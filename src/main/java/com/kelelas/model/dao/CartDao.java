package com.kelelas.model.dao;

import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.Cart;
import com.kelelas.model.entity.Dish;

import java.util.List;

public interface CartDao extends GenericDao<Cart> {
    void deleteByUserId(Cart cart);
    List<DishDTO> getLocalCart(String locale, int userId);
    List<Dish> getCart(int userId);
}
