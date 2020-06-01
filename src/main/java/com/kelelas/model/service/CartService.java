package com.kelelas.model.service;

import com.kelelas.model.dao.CartDao;
import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.Cart;
import com.kelelas.model.entity.Dish;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CartService {
    DaoFactory factory;
    CartDao dao;

    public CartService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createCartDao();
    }
    public void addToCart(Cart cart){
        dao.create(cart);
    }

    public void deleteFromCart(Cart cart){
        dao.deleteByUserId(cart);
    }

    public List<DishDTO> getLocalCart(HttpServletRequest request){
        return dao.getLocalCart(request.getSession().getAttribute("lang").toString(), (Integer) request.getSession().getAttribute("userId"));
    }

    public List<Dish> getCart(int userId){
        return dao.getCart(userId);
    }

    public void clearCart(int userId){
        dao.delete(userId);
    }

}
