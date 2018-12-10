/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.data.repository;

import com.mycompany.shopbackendspring.data.entity.Cartrecipedb;
import com.mycompany.shopbackendspring.data.entity.CartrecipedbID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nergal
 */
@Repository
public interface CartrecipedbRepository extends JpaRepository<Cartrecipedb, CartrecipedbID> {

    @Query("SELECT crdb FROM Cartrecipedb crdb WHERE crdb.cartrecipedbID.orderid = :orderid")
    List<Cartrecipedb> getCartsForOrderID(@Param("orderid") int orderid);
}
