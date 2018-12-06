/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.data.entity.Cart;
import com.mycompany.data.entity.CartItem;
import com.mycompany.data.entity.Cartdb;
import com.mycompany.data.entity.Cartrecipedb;
import com.mycompany.data.entity.Recipe;
import com.mycompany.data.repository.CartRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nergal
 */
@Service
public class CartService {

//    private final CartRepository cartRepository;
//
//    @Autowired
//    public CartService(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }
    public Cartdb createNewCart(Cart cart) {
        // generate order number
        final int rnd = 100 + (int) (Math.random() * 10000);
        // prepare an element Cartdb fo DB
        if (!cart.getName().equals("") && (cart.getPrice() != 0) && !cart.getEmail().equals("") && (cart.getCart().length != 0)) {
            Cartdb cartTemp = new Cartdb(cart.getName(), cart.getPrice(), cart.getEmail(), cart.getSex(), rnd);
            int totalPrice = cart.getPrice();
            // Get list of goods
            CartItem[] cartTempArr = cart.getCart();
            Set<Cartrecipedb> cartRecipeTempDb = new HashSet<>();
            for (CartItem cartItemTemp : cartTempArr) {
                Cartrecipedb cartRecTemp = new Cartrecipedb(); //  prepare element for Cartrecipedb
                cartRecTemp.setOrderid(rnd);
                Recipe cartRec = cartItemTemp.getRec();  // take another element and get data from it
                cartRecTemp.setId(cartRec.getId());
                cartRecTemp.setName(cartRec.getName());
                cartRecTemp.setPrice(cartRec.getPrice());
                cartRecTemp.setDescription(cartRec.getDescription());
                cartRecTemp.setImagepath(cartRec.getImagepath());
                cartRecTemp.setNumb(cartItemTemp.getNumb());
                cartRecipeTempDb.add(cartRecTemp);
                System.out.println(cartRecTemp.getName()+" "+cartRecTemp.getNumb());
                totalPrice = totalPrice-cartRec.getPrice()*cartItemTemp.getNumb(); // check if the totalprice is correct 
            }
            if (totalPrice == 0){
                cartTemp.setCartrecipedb(cartRecipeTempDb);
                //       String s1 = this.cartRepository.storeCartdb(cartTemp);
                //       System.out.println(s1);
                return cartTemp;
            } else {
                System.out.println("Total price is different by "+totalPrice);
                return null;
            }
        } else {
            System.out.println("There were empty data tn the fields");
            return null;
        }
    }

}
