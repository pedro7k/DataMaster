package com.pedro.domain.form.repository;

import com.pedro.domain.form.model.vo.UserManageVO;
import com.pedro.domain.form.model.vo.UserRoleVO;

import java.util.List;

/**
 * UserManageForm仓储服务
 */
public interface UserManageFormRepository {

    /**
     * 拉取用户管理表单数据
     */
    List<UserManageVO> loadUserManageForm();

    /**
     * 删除用户
     */
    int deleteUsersById(List<Integer> uidList);

    /**
     * 执行用户权限编辑
     */
    int editUserRole(UserRoleVO userRoleVO);
}
