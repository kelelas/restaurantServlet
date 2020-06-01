package com.kelelas.model.dao.impl;

import com.kelelas.model.dao.TransactionDao;
import com.kelelas.model.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDaoImpl implements TransactionDao {
    private Connection connection;

    public TransactionDaoImpl(Connection connection) {
        this.connection = connection;
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

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
