/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nergal
 */
@Embeddable
public class CartrecipedbID implements Serializable {

    @NotNull
    @Column(name = "orderid")
    private int orderid;

    @NotNull
    @Column(name = "id")
    private int id;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartrecipedbID() {
    }

    public CartrecipedbID(int orderid, int id) {
        this.orderid = orderid;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.orderid;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartrecipedbID other = (CartrecipedbID) obj;
        if (this.orderid != other.orderid) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
