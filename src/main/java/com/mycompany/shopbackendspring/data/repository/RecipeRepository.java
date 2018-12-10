/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.data.repository;

import com.mycompany.shopbackendspring.data.entity.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nergal
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    public Recipe findById(String number);

    @Query("SELECT r FROM Recipe r")
    public List<Recipe> getRecipies();
}
