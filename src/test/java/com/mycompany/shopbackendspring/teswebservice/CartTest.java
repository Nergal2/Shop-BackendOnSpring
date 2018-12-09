/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.teswebservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.shopbackendspring.data.entity.Cart;
import com.mycompany.shopbackendspring.data.entity.CartItem;
import com.mycompany.shopbackendspring.data.entity.Cartdb;
import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.service.CartService;
import com.mycompany.shopbackendspring.webservice.CartController;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 *
 * @author Nergal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CartController.class)
public class CartTest {

    @MockBean
    private CartService cartService;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    String jsonCart = "{\"name\": \"Vegan\",\"email\":\"vegan@yoga.ru\",\"price\":600,\"sex\":\"Androgyne — гермафродит\", "
            + "\"cart\":[{\"numb\": 3,\"rec\":{\"description\":\"Черный кабачок\",\"id\": 7899.450795102266,"
            + "\"imagepath\": \"https://mcdn01.gittigidiyor.net/23724/tn30/237241576_tn30_0.jpg\", \"name\": \"Баклажан\","
            + "\"price\": 300}}]}";

    @Test
    public void storeCartTest() throws Exception {
        Cart cart1 = mapper.readValue(jsonCart, Cart.class);

        Cartdb cartdb = new Cartdb("Vegan", 600, "vegan@yoga.ru", "Androgyne — гермафродит", 0);
        given(cartService.createNewCart(cart1)).willReturn(cartdb);

        this.mockMvc.perform(post("/rest/cart/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCart))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Vegan")));
    }

    @Test
    public void storeCartServiceTest() throws Exception {
//        Cart cart = new Cart("name", "email", "sex", 221, "20");
//        CartItem ci = new CartItem();
//        ci.setRec(new Recipe(220, "Tomatoes", 220));
//        CartItem[] ciarr = new CartItem[1];
//        ciarr[0] = ci;
//        cart.setCart(ciarr);
        Cart cart = mapper.readValue(jsonCart, Cart.class);
        Cartdb cartdb = cartService.createNewCart(cart);
        //Cartdb cartdb = new Cartdb();
        given(cartService.createNewCart(cart)).willReturn(cartdb);
    }
}
