/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.webservice;

import com.mycompany.shopbackendspring.data.entity.Cart;
import com.mycompany.shopbackendspring.data.entity.Cartdb;
import com.mycompany.shopbackendspring.service.AuthService;
import com.mycompany.shopbackendspring.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nergal
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/rest/cart/new", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public Cart storeCart(@RequestBody Cart cart) {
        System.out.println(cart.getName());
        Cartdb cart1 = cartService.createNewCart(cart);
        //  return cart1.getName();
        if (null != cart1) {
            cart.setName(cart1.getName());
            return cart;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/rest/cart/all", method = RequestMethod.GET, produces = "application/json")
    public List<Cart> getAllCarts(@RequestParam("auth") String tokenAuth) {
        System.out.println("Request with token: " + tokenAuth);
        if (this.authService.isAuthorized(tokenAuth)) {
            return cartService.getAllCarts();
        } else {
            return null;//Response.status(403).build();
        }
    }

    @RequestMapping(value = "/rest/cart/all", method = RequestMethod.PUT,
            produces = "application/json", consumes = "application/json")
    public String storeAllCarts(@RequestParam("auth") String tokenAuth, @RequestBody List<Cart> carts) {
        System.out.println("Request saving carts: " + tokenAuth);
        if (this.authService.isAuthorized(tokenAuth)) {
            return cartService.storeAllCartsDB(carts);
        } else {
            return null;//Response.status(403).build();
        }
    }

}
