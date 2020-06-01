package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.CartDao;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.Cart;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kelelas.model.dao.impl.DishDaoImpl.*;

public class CartDaoImpl implements CartDao {
    private Connection connection;

    public CartDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Cart entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("cart.addNewPositionIntoCart"))) {
            ps.setInt(1, entity.getDishId());
            ps.setInt(2, entity.getUserId());

            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }

    }

    @Override
    public Optional<Cart> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public void update(Cart entity) {

    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("cart.deletePositionsFromCartByUserId"))) {
            ps.setInt(1, id);

            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }
    }

    public void deleteByUserId(Cart cart) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("cart.deleteOnePositionFromCartById"))) {
            ps.setInt(1, cart.getDishId());
            ps.setInt(2, cart.getUserId());

            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }


    }

    @Override
    public List<DishDTO> getLocalCart(String locale, int userId) {
        List<DishDTO> result;
        Map<Integer, DishDTO> dishes = new HashMap<>();
        try {
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps =  connection.prepareStatement(
                        QueryConfig.getProperty("cart.getUkrCart"));
            }else {
                ps =  connection.prepareStatement(
                        QueryConfig.getProperty("cart.getEngCart"));
            }
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DishDTO dish = extractLocaleDishFromResultSet(rs);
                makeUniqueDishDTO(dishes, dish);

            }
            result = dishes.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(dishes.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<Dish> getCart(int userId) {
        List<Dish> result;
        Map<Integer, Dish> dishes = new HashMap<>();
        try {
            PreparedStatement ps =  connection.prepareStatement(
                    QueryConfig.getProperty("cart.getFullCart"));
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = extractFromResultSet(rs);
                makeUniqueDish(dishes, dish);



            }
            result = dishes.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(dishes.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
