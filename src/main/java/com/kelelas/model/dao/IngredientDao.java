package com.kelelas.model.dao;

import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Ingredient;

import java.util.List;

public interface IngredientDao extends GenericDao<Ingredient> {
   List<IngredientDTO> getLocaleIngredients(String locale);
}
