/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Nergal
 */
@Service
public class EncryptingService {

    static BASE64Encoder enc = new BASE64Encoder();
    static BASE64Decoder dec = new BASE64Decoder();
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String base64encode(Integer text) {
        try {
            return enc.encode(text.toString().getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static int base64decode(String text) {
        try {
            return Integer.parseInt(new String(dec.decodeBuffer(text), DEFAULT_ENCODING));
        } catch (IOException e) {
            return 0;
        }
    }
}
