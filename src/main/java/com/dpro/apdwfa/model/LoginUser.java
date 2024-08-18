package com.dpro.apdwfa.model;

import java.util.Set;

public class LoginUser {
    private String userName;
    private String UserId;
    private Set<String> menus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId =UserId;
    }

    public Set<String> getMenus() {
        return menus;
    }

    public void setMenus(Set<String> menus) {
        this.menus = menus;
    }

}
