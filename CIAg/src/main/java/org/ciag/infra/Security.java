/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.infra;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 *
 * @author ettore
 */
@Singleton
public class Security {

    @Produces
    MessageDigest messageDigest;

    public Security() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA-256");
    }

}
