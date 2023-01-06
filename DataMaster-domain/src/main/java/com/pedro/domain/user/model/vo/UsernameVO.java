package com.pedro.domain.user.model.vo;

/**
 * 用户名VO，用于返回给前端展示
 */
public class UsernameVO {

    String username;

    public UsernameVO() {
    }

    public UsernameVO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
