/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.data.repository;

import com.mycompany.data.entity.Cart;
import com.mycompany.data.entity.Cartdb;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nergal
 */
@Repository
public interface CartRepository extends CrudRepository<Cartdb, Integer> {

    List<Cartdb> getCarts();

    String storeCartdb(Cartdb cartdb);

    String storeAllCartsDb(List<Cart> carts);
}
