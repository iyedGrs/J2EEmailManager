package com.example.jdbcemaifetchernew.model;

public class Email {
    private int id;
    private String email;

    public Email(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
