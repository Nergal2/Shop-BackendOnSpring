/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.data.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Администратор
 */
@XmlRootElement

public class Cart implements Serializable {

    @Basic(optional = false)

    private String name;

    @Basic(optional = false)

    private String email;

    @Basic(optional = false)

    private String sex;

    @Basic(optional = true)

    private CartItem[] cart;

    @Basic(optional = false)

    private int price;

    @Basic(optional = true)
    private String orderId;

    public Cart() {
    }

    public Cart(String name, String email, String sex, int price, String orderId) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.price = price;
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CartItem[] getCart() {
        return cart;
    }

    public void setCart(CartItem[] cart) {
        this.cart = cart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
