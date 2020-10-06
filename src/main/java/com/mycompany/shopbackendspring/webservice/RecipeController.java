/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.webservice;

import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.service.AuthService;
import com.mycompany.shopbackendspring.service.RecipeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nergal
 */
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/rest/recipies/all", method = RequestMethod.GET, produces = "application/json")
    public List<Recipe> findAll() {
        return recipeService.findAll();
    }

    @RequestMapping(value = "/rest/recipies/all", method = RequestMethod.PUT,
            produces = "application/json", consumes = "application/json")
    public String storeAllRecipies(@RequestParam("auth") String tokenAuth, @RequestBody List<Recipe> recipies) {
        System.out.println("Request saving rec: " + tokenAuth);
        if (this.authService.isAuthorized(tokenAuth)) {
            return recipeService.storeAllRecipiesDB(recipies);
        } else {
            return null;//Response.status(403).build();
        }
    }

    @GetMapping("/echo")
    public String echo() {
        return "smth recipie";
    }

    @RequestMapping(value = "/rest/recipies/all1", method = RequestMethod.GET)
    public List<Recipe> getAllItems() {
        List<Recipe> lst = new ArrayList<>();
        lst.add(new Recipe(220, "Tomatoes", 220));
        lst.get(0).setDescription("Fresh tomatoes");
        lst.get(0).setImagepath("https://i.ytimg.com/vi/9DIri21SII8/maxresdefault.jpg");
        lst.add(new Recipe(230, "Морковь", 280));
        lst.get(1).setDescription("Fresh carrots");
        lst.get(1).setImagepath("https://wplimb.com/demo/wp-content/uploads/2017/03/Carrot-F1-Red-768x768.jpg");
        System.out.println("Recipies list's been requested");
        return lst;
    }

}
