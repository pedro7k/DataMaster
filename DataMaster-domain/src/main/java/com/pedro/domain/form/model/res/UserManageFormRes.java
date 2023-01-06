package com.pedro.domain.form.model.res;

public class UserManageFormRes {

    private int uid;

    private String username;

    /**
     * role转为中文形式
     */
    private String role;

    public UserManageFormRes() {
    }

    public UserManageFormRes(int uid, String username, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
