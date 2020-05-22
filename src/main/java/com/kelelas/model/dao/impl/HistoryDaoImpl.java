package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.HistoryDao;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.dto.HistoryDTO;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.History;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class HistoryDaoImpl implements HistoryDao {
    private Connection connection;

    public HistoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(History entity) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps1 = connection.prepareStatement(QueryConfig.getProperty("history.createNewHistory"), Statement.RETURN_GENERATED_KEYS);
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
                PreparedStatement ps2 = connection.prepareStatement(QueryConfig.getProperty("history.createNewManyToManyHistoryDishes"));
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
    public Optional<History> findById(int id) {
        History history = null;
        Map<Integer, Dish> dishes = new HashMap<>();
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("history.findOneHistoryItemById"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                history = extractFromResultSet(rs);
                Dish dish = DishDaoImpl.extractFromResultSet(rs);
                Ingredient ingredient = IngredientDaoImpl.extractFromResultSet(rs);
                dish = DishDaoImpl.makeUniqueDish(dishes, dish);
                dish.getIngredients().add(ingredient);


            }
            if (history != null) {
                history.setDishes(dishes.values().stream().collect(Collectors.toList()));
            }
        }catch (Exception e){
            throw new DBException(e);
        }

        return Optional.ofNullable(history);
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    @Override
    public void update(History entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("history.update"))) {
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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private History extractFromResultSet(ResultSet rs) throws SQLException {
        return new History.Builder()
                .id(rs.getInt("h.id"))
                .date(rs.getTimestamp("h.date").toLocalDateTime())
                .price(rs.getInt("h.price"))
                .status(rs.getInt("h.status_id"))
                .userId(rs.getInt("h.users_id"))
                .build();

    }

    @Override
    public List<HistoryDTO> getLocaleStories(String locale, int offset, int amountOfRecords) {
        List<HistoryDTO> result;
        Map<Integer, HistoryDTO> stories = new HashMap<>();
        try{
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllUkrStories"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllEngStories"));
            }
            ps.setInt(1, offset);
            ps.setInt(2, amountOfRecords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistoryDTO history = extractLocaleHistoryFromResultSet(rs);
                DishDTO dish =DishDaoImpl.extractLocaleDishFromResultSet(rs);
                history = makeUniqueHistoryDTO(stories, history);
                history.getDishes().add(dish);

            }
            result = stories.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(stories.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<HistoryDTO> getLocaleStoriesByStatusAndUserId(String locale, int userId, int status) {
        List<HistoryDTO> result = new ArrayList<>();
        Map<Integer, HistoryDTO> stories = new HashMap<>();
        try {
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllUkrStoriesByStatusAndUserId"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllEngStoriesByStatusAndUserId"));
            }
            ps.setInt(1, status);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistoryDTO history = extractLocaleHistoryFromResultSet(rs);
                DishDTO dish =DishDaoImpl.extractLocaleDishFromResultSet(rs);
                history = makeUniqueHistoryDTO(stories, history);
                history.getDishes().add(dish);

            }
            result = stories.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(stories.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<HistoryDTO> getLocaleStoriesByStatus(String locale, int status) {
        List<HistoryDTO> result = new ArrayList<>();
        Map<Integer, HistoryDTO> stories = new HashMap<>();
        try {
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllUkrStoriesByStatus"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllEngStoriesByStatus"));
            }
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistoryDTO history = extractLocaleHistoryFromResultSet(rs);
                DishDTO dish =DishDaoImpl.extractLocaleDishFromResultSet(rs);
                history = makeUniqueHistoryDTO(stories, history);
                history.getDishes().add(dish);

            }
            result = stories.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(stories.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<HistoryDTO> getLocaleStoriesByUserId(String locale, int userId) {
        List<HistoryDTO> result = new ArrayList<>();
        Map<Integer, HistoryDTO> stories = new HashMap<>();
        try {
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllUkrStoriesByUserId"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("history.selectAllEngStoriesByUserId"));
            }
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistoryDTO history = extractLocaleHistoryFromResultSet(rs);
                DishDTO dish =DishDaoImpl.extractLocaleDishFromResultSet(rs);
                history = makeUniqueHistoryDTO(stories, history);
                history.getDishes().add(dish);

            }
            result = stories.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(stories.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    static HistoryDTO extractLocaleHistoryFromResultSet(ResultSet rs) throws SQLException {
        return new HistoryDTO.Builder()
                .id(rs.getInt("h.id"))
                .date(rs.getTimestamp("h.date").toLocalDateTime())
                .price(rs.getInt("h.price"))
                .status(rs.getString("s_status"))
                .userName(rs.getString("u_user"))
                .build();
    }

    private HistoryDTO makeUniqueHistoryDTO(Map<Integer, HistoryDTO> stories, HistoryDTO history) {
        stories.putIfAbsent(history.getId(), history);
        return stories.get(history.getId());
    }

    @Override
    public int numberOfRowsInTable() {
        int numOfRows = 0;

        try(Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery("SELECT COUNT(Id) FROM history") ;
            if(rs.next()) {
                numOfRows = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new DBException(e);
        }

        return numOfRows;
    }

    @Override
    public void setAutoCommitFalse(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void commit() {
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
}
