package com.aditya.framework.model;

public class User {
    private final String username;
    private final String password;
    private final String role;

    public User(String role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}