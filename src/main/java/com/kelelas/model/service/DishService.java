package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.DishDao;
import com.kelelas.model.dto.DishDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DishService {
    DaoFactory factory;
    DishDao dao;

    public DishService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createDishDao();
    }

    public List<DishDTO> getLocaleDishes(HttpServletRequest request) {
        return dao.getLocaleDishes(request.getSession().getAttribute("lang").toString());

    }

}
