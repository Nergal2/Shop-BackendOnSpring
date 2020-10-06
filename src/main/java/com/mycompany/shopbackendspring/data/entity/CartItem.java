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
 * Сущность корзины продуктов
 */
@XmlRootElement
public class CartItem implements Serializable {

    @Basic(optional = false)

    private Recipe rec;

    @Basic(optional = false)

    private int numb;

    public CartItem() {
    }

    public Recipe getRec() {
        return rec;
    }

    public void setRec(Recipe rec) {
        this.rec = rec;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}
