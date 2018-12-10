/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.service;

import com.mycompany.shopbackendspring.data.entity.Cart;
import com.mycompany.shopbackendspring.data.entity.CartItem;
import com.mycompany.shopbackendspring.data.entity.Cartdb;
import com.mycompany.shopbackendspring.data.entity.Cartrecipedb;
import com.mycompany.shopbackendspring.data.entity.CartrecipedbID;
import com.mycompany.shopbackendspring.data.entity.Recipe;
import com.mycompany.shopbackendspring.data.repository.CartRepository;
import com.mycompany.shopbackendspring.data.repository.CartrecipedbRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nergal
 */
@Service
public class CartService {

    private final String admin = "admin@admin.ruadmin male";

    public static final String DEFAULT_ENCODING = "UTF-8";

    private final CartRepository cartRepository;
    private final CartrecipedbRepository cartrecipedbRepository;
    private final EncryptingService encryptingService;

    @Autowired
    public CartService(CartRepository cartRepository, CartrecipedbRepository cartrecipedbRepository, EncryptingService encryptingService) {
        this.cartRepository = cartRepository;
        this.cartrecipedbRepository = cartrecipedbRepository;
        this.encryptingService = encryptingService;
    }

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
                //     cartRecTemp.getCartrecipedbID().setOrderid(rnd);
                Recipe cartRec = cartItemTemp.getRec();  // take another element and get data from it
                CartrecipedbID cartRecTempID = new CartrecipedbID(rnd, cartRec.getId());
                // cartRecTemp.getCartrecipedbID().setId(cartRec.getId());
                cartRecTemp.setCartrecipedbID(cartRecTempID);
                cartRecTemp.setName(cartRec.getName());
                cartRecTemp.setPrice(cartRec.getPrice());
                cartRecTemp.setDescription(cartRec.getDescription());
                cartRecTemp.setImagepath(cartRec.getImagepath());
                cartRecTemp.setNumb(cartItemTemp.getNumb());
                cartRecipeTempDb.add(cartRecTemp);
                System.out.println(cartRecTemp.getName() + " " + cartRecTemp.getNumb());
                totalPrice = totalPrice - cartRec.getPrice() * cartItemTemp.getNumb(); // check if the totalprice is correct 
            }
            if (totalPrice == 0) {
                cartTemp.setCartrecipedb(cartRecipeTempDb);
                String s1 = this.cartRepository.save(cartTemp).getName();
                System.out.println("Save new  cart name: " + s1);
                return cartTemp;
            } else {
                System.out.println("Total price is different by " + totalPrice);
                return null;
            }
        } else {
            System.out.println("There were empty data tn the fields");
            return null;
        }
    }

    public List<Cart> getAllCarts() {
        List<Cart> cartArr = new ArrayList<>();
        List<Cartdb> cartdb = this.cartRepository.getCarts();
        for (Cartdb cdb : cartdb) {
            String idTemp = encryptingService.base64encode(cdb.getOrderid());
            Cart cart = new Cart(cdb.getName(), cdb.getEmail(), cdb.getSex(), cdb.getPrice(), idTemp);
            cart.setCart(getCartRecipiesDB(cdb.getOrderid()));
            cartArr.add(cart);
        }
        return cartArr;
    }

    public String storeAllCartsDB(List<Cart> carts) {
        List<Integer> newtids = new ArrayList<>();
        List<Cartdb> oldArr = this.cartRepository.findAll();
        for (Cart cart : carts) {
            newtids.add(this.encryptingService.base64decode(cart.getOrderId()));
        }
        for (Cartdb cartdb : oldArr) {
            if (!newtids.contains(cartdb.getOrderid())) {
                this.cartRepository.deleteById(cartdb.getOrderid());
            }
        }
        return "Database: carts updated";
    }

    private CartItem[] getCartRecipiesDB(int id) {
        if (id != 0) {
            List<Cartrecipedb> result = cartrecipedbRepository.getCartsForOrderID(id);
            List<CartItem> cram = new ArrayList<>();
            for (Cartrecipedb res : result) {
                CartItem ci = new CartItem();
                Recipe rec = new Recipe(res.getCartrecipedbID().getId(), res.getName(), res.getPrice());
                rec.setDescription(res.getDescription());
                rec.setImagepath(res.getImagepath());
                ci.setNumb(res.getNumb());
                ci.setRec(rec);
                cram.add(ci);
            }
            CartItem[] y = cram.toArray(new CartItem[cram.size()]);
            return y;
        } else {
            return null;
        }
    }

}
