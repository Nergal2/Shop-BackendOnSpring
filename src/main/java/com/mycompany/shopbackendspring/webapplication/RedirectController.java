/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.webapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nergal
 */
@Controller
public class RedirectController {

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String getCatalogue() {
        System.out.println("Redirection");
        return "redirect:/";
    }

    @RequestMapping(value = "/catalogue/{id}", method = RequestMethod.GET)
    public String getCatalogueId(@PathVariable("id") String s) {
        System.out.println("Redirection " + s);
        return "redirect:/";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCart() {
        System.out.println("Redirection");
        return "redirect:/";
    }
}
