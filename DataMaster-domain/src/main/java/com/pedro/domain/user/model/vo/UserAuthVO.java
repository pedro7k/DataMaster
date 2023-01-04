package com.pedro.domain.user.model.vo;

/**
 * 用户认证VO
 */
@Deprecated
public class UserAuthVO {

    String username;

    String password;

    public UserAuthVO() {
    }

    public UserAuthVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
