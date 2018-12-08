/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.service;

import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.data.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nergal
 */
@Service
public class RecipeService {
    
    @Autowired
    private RecipeRepository repository;
    
    public List<Recipe> findAll() {
        return this.repository.getRecipies();
      }
}
