package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.IngredientDao;
import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class IngredientService {
    DaoFactory factory;
    IngredientDao dao;

    public IngredientService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createIngredientDao();
    }

    public Ingredient getIngredientById(int id) {
        return dao.findById(id).orElseThrow(DBException::new);
    }

    public List<IngredientDTO> getLocaleIngredients(HttpServletRequest request) {
        String locale = request.getSession().getAttribute("lang").toString();
        return dao.getLocaleIngredients(locale);
    }

    public void save(Ingredient item){
        dao.update(item);
    }

}
