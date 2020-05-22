package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.IngredientDao;
import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import java.sql.*;
import java.util.*;

public class IngredientDaoImpl implements IngredientDao {
    private Connection connection;

    public IngredientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Ingredient entity) {

    }

    @Override
    public Optional<Ingredient> findById(int id) {
        Ingredient ingredient = null;
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("ingredient.selectIngredientById"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingredient = extractFromResultSet(rs);
            }
        }catch (Exception e){
            throw new DBException(e);
        }

        return Optional.ofNullable(ingredient);
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    QueryConfig.getProperty("ingredient.selectAllIngredients"));
            while (rs.next()) {
                extractFromResultSet(rs);
                Ingredient ingredient = extractFromResultSet(rs);
                result.add(ingredient);

            }
        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public void update(Ingredient entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("ingredient.updateIngredientById"))) {

            ps.setString(1, entity.getNameUkr());
            ps.setString(2, entity.getNameEng());
            ps.setInt(3, entity.getAmount());
            ps.setInt(4, entity.getMaxAmount());
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

    @Override
    public List<IngredientDTO> getLocaleIngredients(String locale){
        List<IngredientDTO> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs;
            if (locale.equals("ua")) {
                rs = ps.executeQuery(
                        QueryConfig.getProperty("ingredient.selectUkrIngredients"));
            }else {
                rs = ps.executeQuery(
                        QueryConfig.getProperty("ingredient.selectEngIngredients"));
            }
            while (rs.next()) {
                IngredientDTO ingredient = extractLocaleIngredientFromResultSet(rs);
                result.add(ingredient);

            }
        }catch (Exception e){
            throw new DBException(e);
        }
        return result;
    }

    static Ingredient extractFromResultSet(ResultSet rs) throws SQLException {
        return new Ingredient.Builder()
                 .id(rs.getInt("i.id"))
                 .nameUkr(rs.getString("i.name_ukr"))
                 .nameEng(rs.getString("i.name_eng"))
                 .amount(rs.getInt("i.amount"))
                 .maxAmount(rs.getInt("i.max_amount"))
                 .build();

    }

    static IngredientDTO extractLocaleIngredientFromResultSet(ResultSet rs) throws SQLException {
        return new IngredientDTO.Builder()
                .id(rs.getInt("i.id"))
                .name(rs.getString("i_name"))
                .amount(rs.getInt("i.amount"))
                .maxAmount(rs.getInt("i.max_amount"))
                .build();

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
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
