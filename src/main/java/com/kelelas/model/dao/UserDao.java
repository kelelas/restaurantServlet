package com.kelelas.model.dao;


import com.kelelas.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User>{
    Optional<User> findByEmail(String email);
}
