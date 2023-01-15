package com.pedro.common.enums;

import com.pedro.common.exceptions.ServiceException;

import java.util.*;

/**
 * 用户角色枚举
 */
public enum UserRoleEnum {

    ROOT(0, "ROOT用户"),
    ADMIN(1, "管理员"),
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
     * 返回key对应的roles及权限比他低的roles
     *
     * @param roleKey
     * @return
     */
    public static List<String> getRoleString(int roleKey) {
        return roleStringListMap.get(roleKey);
    }

    /**
     * 返回key对应的中文名
     */
    public static String castRoleToString(int roleKey) {
        // 遍历查找
        for (UserRoleEnum enumItem : UserRoleEnum.values()) {
            if (roleKey == enumItem.level) {
                return enumItem.msg;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }

    /**
     * 返回中文名对应的key
     */
    public static int castRoleToInt(String roleString) {
        // 遍历查找
        for (UserRoleEnum enumItem : UserRoleEnum.values()) {
            if (roleString.equals(enumItem.msg)) {
                return enumItem.level;
            }
        }

        throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
    }
}
