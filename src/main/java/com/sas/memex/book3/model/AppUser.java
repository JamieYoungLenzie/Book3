package com.sas.memex.book3.model;

import org.springframework.stereotype.Component;

@Component
public class AppUser {    
    
    private int id;
    private String username;
    private String password;
    private String role;
    
    public AppUser() {        
    }
    
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
