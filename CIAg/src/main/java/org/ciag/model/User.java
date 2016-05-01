/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciag.model;

import com.sun.mail.iap.ByteArray;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.ciag.infra.Connection;

/**
 *
 * @author ettore
 */
@Entity
@Table(name = "ciag_user")
@Connection("default")
@XmlRootElement(name = "user")
public class User implements Serializable {

    @Inject
    private transient MessageDigest messageDigest;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private byte[] password;

    @Column
    private String email;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = messageDigest.digest(password.getBytes());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{ id=" + id + ", name=" + name + ", password=********, email=" + email + '}';
    }

}
