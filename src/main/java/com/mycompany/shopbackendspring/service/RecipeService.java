/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.service;

import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.data.repository.RecipeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nergal
 */
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return this.recipeRepository.getRecipies();
    }

    public String storeAllRecipiesDB(List<Recipe> recipies) {
        this.recipeRepository.saveAll(recipies);
        List<Recipe> newArr = this.recipeRepository.findAll();
        for (Recipe rec : newArr) {
            if (!recipies.contains(rec)) {
                this.recipeRepository.delete(rec);
            }
        }
        return "Database: recipies stored";
    }
}
