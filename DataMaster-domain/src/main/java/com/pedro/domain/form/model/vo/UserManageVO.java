package com.pedro.domain.form.model.vo;

/**
 * 用户管理VO
 */
public class UserManageVO {

    private int uid;

    private String username;

    private int role;

    public UserManageVO() {
    }

    public UserManageVO(int uid, String username, int role) {
        this.uid = uid;
        this.username = username;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
