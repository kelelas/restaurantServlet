package com.kelelas.model.dao;

public interface TransactionDao extends AutoCloseable {
    void setAutoCommitFalse();
    void commit();
    void rollback();
}
