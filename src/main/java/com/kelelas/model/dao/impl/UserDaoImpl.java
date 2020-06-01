package com.kelelas.model.dao.impl;

import com.kelelas.controller.config.QueryConfig;
import com.kelelas.model.dao.UserDao;
import com.kelelas.model.entity.RoleType;
import com.kelelas.model.entity.User;
import com.kelelas.model.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("user.saveNewUser"))) {
            ps.setString(1, entity.getNameUkr());
            ps.setString(2, entity.getNameEng());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setBoolean(5, entity.isActive());
            ps.setString(6, String.valueOf(entity.getRole()));
            ps.setInt(7, entity.getBalance());

            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                QueryConfig.getProperty("user.updateUser"))) {
            ps.setString(1, entity.getNameUkr());
            ps.setString(2, entity.getNameEng());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setBoolean(5, entity.isActive());
            ps.setString(6, String.valueOf(entity.getRole()));
            ps.setInt(7, entity.getBalance());
            ps.setInt(8, entity.getId());
            ps.executeUpdate();

        }catch (Exception e){
            throw new DBException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(QueryConfig.getProperty("user.selectByEmail"))) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = extractFromResultSet(rs);
            }
        }catch (Exception e){
            throw new DBException(e);
        }
        return Optional.ofNullable(user);
    }

    private User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getInt("id"))
                .nameUkr(rs.getString("name_ukr"))
                .nameEng(rs.getString("name_eng"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .isActive(rs.getBoolean("is_active"))
                .role(RoleType.valueOf(rs.getString("role")))
                .balance(rs.getInt("balance"))
                .build();

    }


}
