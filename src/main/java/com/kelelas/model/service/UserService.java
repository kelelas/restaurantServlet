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

    public User findByEmail(String email){
        return dao.findByEmail(email).orElseThrow(DBException::new);
    }


    public void save(User user){
        try {
            dao.create(user);
        }catch (Exception e){
            throw new DBException(e);
        }
    }

    public void update(User user) {
        try {
            dao.update(user);
        }catch (Exception e){
            throw new DBException(e);
        }
    }
}
