package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.TransactionDao;

public class TransactionService {
    DaoFactory daoFactory;
    TransactionDao dao;

    public TransactionService() {
        this.daoFactory = DaoFactory.getInstance();
        this.dao = daoFactory.createTransactionDao();
    }

    public void setAutoCommitFalse(){
        dao.setAutoCommitFalse();
    }

    public void commit() {
        dao.commit();
    }

    public void rollback() {
        dao.rollback();
    }
}
