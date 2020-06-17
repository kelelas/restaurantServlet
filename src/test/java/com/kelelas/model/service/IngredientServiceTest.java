package com.kelelas.model.service;


import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.IngredientDao;
import com.kelelas.model.dto.IngredientDTO;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @Mock
    DaoFactory factory;

    @Mock
    IngredientDao dao;

    @InjectMocks
    IngredientService ingredientService;


    private final int id = 6;
    private final Ingredient ingredient = new Ingredient.Builder()
            .amount(100)
            .maxAmount(100)
            .id(id)
            .nameEng("ingredient")
            .nameUkr("інгредієнт")
            .build();
    private final IngredientDTO ingredientEng = new  IngredientDTO.Builder()
            .amount(100)
            .maxAmount(100)
            .id(id)
            .name("ingredient")
            .build();

    @Test
    public void getIngredientById() {
        when(dao.findById(id)).thenReturn(Optional.of(ingredient));
        assertEquals(ingredientService.getIngredientById(id),ingredient);
        verify(dao).findById(id);
    }

    @Test
    public void getLocaleIngredients() {
        List<IngredientDTO> ingredientsDTO = new ArrayList<>();
        ingredientsDTO.add(ingredientEng);
        String locale = "eng";

        when(dao.getLocaleIngredients(locale)).thenReturn(ingredientsDTO);
        assertEquals(ingredientService.getLocaleIngredients(any()), ingredientsDTO);
        verify(dao).getLocaleIngredients(locale);
    }

    @Test
    public void save() {
        doThrow(new DBException()).when(dao).update(ingredient);
        try {
            ingredientService.save(ingredient);
            fail("no DBException");
        }catch (DBException e){
            assertEquals(e.getClass(), DBException.class);
        }
        verify(dao).update(ingredient);
    }


}