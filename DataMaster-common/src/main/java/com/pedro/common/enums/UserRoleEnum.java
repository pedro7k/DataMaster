package com.pedro.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色枚举
 */
public enum UserRoleEnum {

    ROOT(0, "ROOT用户"),
    ADMIN(1, "管理员用户"),
    NORMAL_USER(2, "普通用户");

    /**
     * 权限等级
     */
    private int level;
    /**
     * 权限信息
     */
    private String msg;

    UserRoleEnum(int level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    public int getLevel() {
        return level;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 静态加载从数字到角色的对应关系
     */
    private static final Map<Integer, List<String>> roleStringListMap = new HashMap<>();

    static {
        // root
        List<String> rootRoles = new ArrayList<>(2);
        rootRoles.add("root");
        rootRoles.add("admin");
        roleStringListMap.put(0, rootRoles);
        // admin
        List<String> adminRoles = new ArrayList<>(1);
        rootRoles.add("admin");
        roleStringListMap.put(1, adminRoles);
        // normal user
        roleStringListMap.put(2, new ArrayList<>());
    }

    /**
     * 返回key对应的roles
     *
     * @param roleKey
     * @return
     */
    public static List<String> getRoleString(int roleKey) {
        return roleStringListMap.get(roleKey);
    }
}