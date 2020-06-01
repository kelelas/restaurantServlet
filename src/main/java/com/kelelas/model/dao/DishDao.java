package com.kelelas.model.dao;

import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishDao extends GenericDao<Dish> {
    List<DishDTO> getLocaleDishes(String locale);
    Optional<DishDTO> getOneLocaleDish(String locale, int id);

}
