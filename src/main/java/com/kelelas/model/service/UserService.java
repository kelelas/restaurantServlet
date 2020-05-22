package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.UserDao;
import com.kelelas.model.entity.User;
import com.kelelas.model.exception.DBException;

public class UserService {
    DaoFactory factory;
    UserDao dao;

    public UserService() {
        factory = DaoFactory.getInstance();
        dao = factory.createUserDao();
    }

//todo change exception to my own
    public User findByEmail(String email) throws Exception {
        return dao.findByEmail(email).orElseThrow(DBException::new);
    }

    public boolean saveNewUser(User user){
        try {
            dao.create(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void save(User user){
        try {
            dao.update(user);
        }catch (Exception e){
            throw new DBException(e);
        }
    }

    public void setAutoCommitFalse() {
        dao.setAutoCommitFalse();
    }

    public void commit(){
        dao.commit();
    }

    public void rollback() {
        dao.rollback();
    }
}
