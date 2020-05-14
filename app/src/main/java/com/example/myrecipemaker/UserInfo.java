package com.example.myrecipemaker;

public class UserInfo {
    public UserInfo() {
    }

    String name;
    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;

    }
}
