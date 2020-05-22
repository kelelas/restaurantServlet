package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.DishDao;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DishDaoImpl implements DishDao {
    private Connection connection;

    public DishDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Dish entity) {

    }

    @Override
    public Optional<Dish> findById(int id) {
        Map<Integer, Dish> dishes = new HashMap<>();
        Dish dish = null;
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("dish.findById"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dish = extractFromResultSet(rs);
                Ingredient ingredient = IngredientDaoImpl.extractFromResultSet(rs);
                dish = makeUniqueDish(dishes, dish);
                dish.getIngredients().add(ingredient);
            }
        }catch (Exception e){
            throw new DBException(e);
        }

        return Optional.ofNullable(dish);
    }

    @Override
    public List<Dish> findAll() {
       List<Dish> result;
       Map<Integer, Dish> dishes = new HashMap<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    QueryConfig.getProperty("dish.findAllDishes"));
            while (rs.next()) {
                Dish dish = extractFromResultSet(rs);
                Ingredient ingredient = IngredientDaoImpl.extractFromResultSet(rs);
                dish = makeUniqueDish(dishes, dish);
                dish.getIngredients().add(ingredient);

            }
            result = dishes.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(dishes.values());

        }catch (Exception e){
            throw new DBException(e);
        }
       return result;
    }

    @Override
    public void update(Dish entity) {

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

    static Dish extractFromResultSet(ResultSet rs) throws SQLException {
        return new Dish.Builder()
                .id(rs.getInt("d.id"))
                .nameUkr(rs.getString("d.name_ukr"))
                .nameEng(rs.getString("d.name_eng"))
                .image(rs.getString("d.image"))
                .price(rs.getInt("d.price"))
                .build();

    }

    static DishDTO extractLocaleDishFromResultSet(ResultSet rs) throws SQLException {
        return new DishDTO.Builder()
                .id(rs.getInt("d.id"))
                .name(rs.getString("d_name"))
                .image(rs.getString("d.image"))
                .price(rs.getInt("d.price"))
                .build();

    }

    @Override
    public List<DishDTO> getLocaleDishes(String locale) {
        List<DishDTO> result = new ArrayList<>();
        Map<Integer, DishDTO> dishes = new HashMap<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs;
            if (locale.equals("ua")) {
                rs = ps.executeQuery(
                        QueryConfig.getProperty("dish.findAllUkrDishes"));
            }else {
                rs = ps.executeQuery(
                        QueryConfig.getProperty("dish.findAllEngDishes"));
            }
            while (rs.next()) {
                DishDTO dish = extractLocaleDishFromResultSet(rs);
                IngredientDTO ingredient = IngredientDaoImpl.extractLocaleIngredientFromResultSet(rs);
                dish = makeUniqueDishDTO(dishes, dish);
                dish.getIngredients().add(ingredient);

            }
            result = dishes.values().stream().collect(Collectors.toList());
            //result = new ArrayList<>(dishes.values());

        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public Optional<DishDTO> getOneLocaleDish(String locale, int id) {
        Map<Integer, DishDTO> dishes = new HashMap<>();
        DishDTO dish = null;
        try{
            PreparedStatement ps;
            if (locale.equals("ua")) {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("dish.findOneUkrDish"));
            }else {
                ps = connection.prepareStatement(
                        QueryConfig.getProperty("dish.findOneEngDish"));
            }
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dish = extractLocaleDishFromResultSet(rs);
                IngredientDTO ingredient = IngredientDaoImpl.extractLocaleIngredientFromResultSet(rs);
                dish = makeUniqueDishDTO(dishes, dish);
                dish.getIngredients().add(ingredient);
            }
        }catch (Exception e){
            throw new DBException(e);
        }

        return Optional.ofNullable(dish);
    }

    static Dish makeUniqueDish(Map<Integer, Dish> dishes, Dish dish) {
        dishes.putIfAbsent(dish.getId(), dish);
        return dishes.get(dish.getId());
    }

     static DishDTO makeUniqueDishDTO(Map<Integer, DishDTO> dishes, DishDTO dish) {
        dishes.putIfAbsent(dish.getId(), dish);
        return dishes.get(dish.getId());
    }

    @Override
    public void setAutoCommitFalse()  {
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
