package com.pedro.domain.user.model.vo;

/**
 * 用户VO
 */
public class UserVO {
    private int uid;
    private String username;
    private String password;
    private String salt;
    private int role;

    public UserVO() {
    }

    public UserVO(int uid, String username, String password, String salt, int role) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
