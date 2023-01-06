package com.pedro.domain.form.repository;

import com.pedro.domain.form.model.vo.UserManageVO;

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
}
