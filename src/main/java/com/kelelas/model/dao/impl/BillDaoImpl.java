package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.BillDao;
import com.kelelas.model.dto.BillDTO;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.Bill;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class BillDaoImpl implements BillDao {
    private Connection connection;

    public BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Bill entity) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps1 = connection.prepareStatement(QueryConfig.getProperty("bill.createNewBill"), Statement.RETURN_GENERATED_KEYS);
            ps1.setTimestamp(1, java.sql.Timestamp.valueOf(entity.getDate()));
            ps1.setInt(2, entity.getPrice());
            ps1.setInt(3, entity.getStatus());
            ps1.setInt(4, entity.getUserId());
            ps1.executeUpdate();
            ResultSet rs = ps1.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            for (Dish dish : entity.getDishes()) {
                PreparedStatement ps2 = connection.prepareStatement(QueryConfig.getProperty("bill.createNewManyToManyBillDishes"));
                ps2.setInt(1, dish.getId());
                ps2.setInt(2, generatedKey);
                ps2.executeUpdate();
            }
            connection.commit();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public Optional<Bill> findById(int id) {
        Bill bill = null;
        Map<Integer, Dish> dishes = new HashMap<>();
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("bill.findOneBillItemById"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bill = extractFromResultSet(rs);
                Dish dish = DishDaoImpl.extractFromResultSet(rs);
                Ingredient ingredient = IngredientDaoImpl.extractFromResultSet(rs);
                dish = DishDaoImpl.makeUniqueDish(dishes, dish);
                dish.getIngredients().add(ingredient);


            }
            if (bill != null) {
                bill.setDishes(dishes.values().stream().collect(Collectors.toList()));
            }
        }catch (Exception e){
            throw new DBException(e);
        }

        return Optional.ofNullable(bill);
    }

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public void update(Bill entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("bill.update"))) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(entity.getDate()));
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getStatus());
            ps.setInt(4, entity.getUserId());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void setAutoCommitFalse() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void commit()  {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private Bill extractFromResultSet(ResultSet rs) throws SQLException {
        return new Bill.Builder()
                .id(rs.getInt("b.id"))
                .date(rs.getTimestamp("b.date").toLocalDateTime())
                .price(rs.getInt("b.price"))
                .status(rs.getInt("b.status_id"))
                .userId(rs.getInt("b.users_id"))
                .build();

    }

    @Override
    public List<BillDTO> getLocaleBillsByStatusAndUserId(String locale, int userId, int status) {
        List<BillDTO> result = new ArrayList<>();
        Map<Integer, BillDTO> bills = new HashMap<>();
        try {
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("bill.selectAllUkrBillsByStatusAndUserId"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("bill.selectAllEngBillsByStatusAndUserId"));
            }
            ps.setInt(1, status);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillDTO bill = extractLocaleBillFromResultSet(rs);
                DishDTO dish =DishDaoImpl.extractLocaleDishFromResultSet(rs);
                bill = makeUniqueBillDTO(bills, bill);
                bill.getDishes().add(dish);

            }
            result = bills.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(stories.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    static BillDTO extractLocaleBillFromResultSet(ResultSet rs) throws SQLException {
        return new BillDTO.Builder()
                .id(rs.getInt("b.id"))
                .date(rs.getTimestamp("b.date").toLocalDateTime())
                .price(rs.getInt("b.price"))
                .status(rs.getString("s_status"))
                .userName(rs.getString("u_user"))
                .build();
    }

    private BillDTO makeUniqueBillDTO(Map<Integer, BillDTO> bills, BillDTO bill) {
        bills.putIfAbsent(bill.getId(), bill);
        return bills.get(bill.getId());
    }
}
