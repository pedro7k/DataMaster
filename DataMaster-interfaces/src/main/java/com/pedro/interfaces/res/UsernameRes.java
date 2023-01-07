package com.pedro.interfaces.res;

/**
 * 用户名Res，用于返回给前端展示
 */
public class UsernameRes {

    String username;

    public UsernameRes() {
    }

    public UsernameRes(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
