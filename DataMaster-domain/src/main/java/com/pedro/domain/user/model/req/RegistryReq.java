package com.pedro.domain.user.model.req;

/**
 * 注册入参
 */
public class RegistryReq {

    String username;

    String password;

    String rePassword;

    public RegistryReq() {
    }

    public RegistryReq(String username, String password, String rePassword) {
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
