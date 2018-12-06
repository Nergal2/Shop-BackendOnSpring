/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webservice;

import com.mycompany.data.entity.Recipe;
import com.mycompany.data.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nergal
 */
@RestController
public class RecipeController {

//    @Autowired
//    private RecipeRepository repository;
//
//    @RequestMapping(value = "/recipies/all1", method = RequestMethod.GET)
//    List<Recipe> findAll() {
//        List<Recipe> r = new ArrayList<>();
//        r = this.repository.getRecipies();
//        return r;
//
//    }
//
//    @RequestMapping(value = "/recipie", method = RequestMethod.GET)
//    List<Recipe> findAll(@RequestParam(required = false) String id) {
//        final List<Recipe> r = new ArrayList<>();
//        if (null == id) {
//            Iterable<Recipe> res = this.repository.getRecipies();
//            res.forEach(recipe -> {
//                r.add(recipe);
//            });
//        } else {
//            Recipe rec = this.repository.findById(id);
//            if (null != rec) {
//                r.add(rec);
//            }
//        }
//        return r;
//    }
    @GetMapping("/echo")
    public String echo() {
        return "smth recipie";
    }

    @RequestMapping(value = "/rest/recipies/all", method = RequestMethod.GET)
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
