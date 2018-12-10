/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.webservice;

import com.mycompany.shopbackendspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nergal
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/rest/login", method = RequestMethod.POST)
    public ResponseEntity loginProcessing(@RequestBody String s) {
        System.out.println("login with " + s);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", this.authService.checkAutorizationForCredentials(s));
        ResponseEntity responseEntity = new ResponseEntity(" ", responseHeaders, HttpStatus.OK);
        return responseEntity;
    }
}
