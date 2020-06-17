package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.IngredientDao;
import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vladyslav Osypchuk
 * @version 4.2
 */
public class IngredientService {
    /**
     * field with DaoFactory class
     */
    DaoFactory factory;
    /**
     *field with specific dao for getting ingredients
     */
    IngredientDao dao;

    public IngredientService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createIngredientDao();
    }

    /**
     * @return ingredient from DB
     * @param id (id of ingredient)
     */
    public Ingredient getIngredientById(int id) {
        return dao.findById(id).orElseThrow(DBException::new);
    }

    /**
     * method use HttpServletRequest to get locale from session to localize ingredients
     * @param request (HttpServletRequest from controller)
     * @return array of all localized ingredients from DB
     */
    public List<IngredientDTO> getLocaleIngredients(HttpServletRequest request) {
        String locale = request.getSession().getAttribute("lang").toString();
        return dao.getLocaleIngredients(locale);
    }

    /**
     * method update ingredient in DB
     * @param item (ingredient witch we saving)
     */
    public void save(Ingredient item){
        dao.update(item);
    }

}
