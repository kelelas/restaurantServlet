package com.kelelas.model.dao.impl;

import com.kelelas.model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public IngredientDao createIngredientDao() {
        return new IngredientDaoImpl(getConnection());
    }

    @Override
    public DishDao createDishDao() {
        return new DishDaoImpl(getConnection());
    }

    @Override
    public HistoryDao createHistoryDao() {
        return new HistoryDaoImpl(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public BillDao createBillDao() {
        return new BillDaoImpl(getConnection());
    }

    @Override
    public CartDao createCartDao() {
        return new CartDaoImpl(getConnection());
    }

    @Override
    public TransactionDao createTransactionDao() {
        return new TransactionDaoImpl(getConnection());
    }


    private Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant_main?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root" ,
                    "root" );
        } catch (SQLException | ClassNotFoundException  e ) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
