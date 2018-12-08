/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.teswebservice;

import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.webservice.RecipeController;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Nergal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipiesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRecipiesTest() throws Exception {
//        List<Recipe> lst = new ArrayList<>();
//        lst.add(new Recipe(220, "Tomatoes", 220));
//        lst.add(new Recipe(230, "Морковь", 280));

        this.mockMvc.perform(get("/rest/recipies/all1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Tomatoes")));
    }

}
