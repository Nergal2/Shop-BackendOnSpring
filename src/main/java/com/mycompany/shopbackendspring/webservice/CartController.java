/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.webservice;

import com.mycompany.shopbackendspring.data.entity.Cart;
import com.mycompany.shopbackendspring.data.entity.Cartdb;
import com.mycompany.shopbackendspring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nergal
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/rest/cart/new", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public Cart storeCart(@RequestBody Cart cart) {
        System.out.println(cart.getName());
        Cartdb cart1 = cartService.createNewCart(cart);
      //  return cart1.getName();
        cart.setName(cart1.getName());
        return cart;
    }
}
