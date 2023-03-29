package com.example.autofix;

public class UsersModel {
    String username;
    String email;
    String userType;
    int id;

    public UsersModel(String username, String email, String userType, int id) {
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }
}
