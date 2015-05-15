/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hccs.inew2438.appPOJO;

/**
 *
 * @author Praveen
 */
public class Customer {

    private String password;
    private String email;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer() {
        super();
    }

    public Customer(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Customer(String password, String email, int id) {
        this.password = password;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" + "password=" + password + ", email=" + email + ", id=" + id + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
