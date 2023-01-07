package com.pedro.domain.form.model.vo;

public class UserRoleVO {

    private int uid;

    private int role;

    public UserRoleVO() {
    }

    public UserRoleVO(int uid, int role) {
        this.uid = uid;
        this.role = role;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
