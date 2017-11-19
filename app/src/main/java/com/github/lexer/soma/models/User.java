package com.github.lexer.soma.models;

public class User {
    public final static User EMPTY = new User("");

    private String name;

    public User(String name) {
        this.name = name;
    }


}
