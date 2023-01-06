package com.pedro.domain.form.service.userManageForm;

import com.pedro.domain.form.model.res.UserManageFormRes;
import com.pedro.domain.form.model.vo.UserManageVO;

import java.util.List;

/**
 * 用户管理表单接口
 */
public interface UserManageFormService {

    /**
     * 表单数据拉取
     */
    List<UserManageFormRes> loadUserManageForm();

    /**
     * 删除单个用户
     */
    void deleteUser(int uid);
}
