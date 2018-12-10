/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Nergal
 */
@Service
public class AuthService {

    private final String token = "token key valueadmin@admin.ruadmin male";
    private final String admin = "admin@admin.ruadmin male";

    public boolean isAuthorized(String tokenAuth) {
        return this.token.equals(tokenAuth);
    }

    public String checkAutorizationForCredentials(String s) {
        if (s.equals(admin)) {
            return this.token;
        } else {
            return "";
        }
    }
}
