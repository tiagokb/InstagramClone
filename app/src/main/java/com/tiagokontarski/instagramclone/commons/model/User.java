package com.tiagokontarski.instagramclone.commons.model;

public class User {
    private String username;
    private UserAuth auth;

    public User(String username, UserAuth auth) {
        this.username = username;
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAuth getAuth() {
        return auth;
    }

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }
}
