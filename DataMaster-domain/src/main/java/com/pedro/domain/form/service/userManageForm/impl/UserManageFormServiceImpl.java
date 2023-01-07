package com.pedro.domain.form.service.userManageForm.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.form.model.res.UserManageFormRes;
import com.pedro.domain.form.model.vo.UserManageVO;
import com.pedro.domain.form.model.vo.UserRoleVO;
import com.pedro.domain.form.repository.UserManageFormRepository;
import com.pedro.domain.form.service.userManageForm.UserManageFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理表单服务
 */
@Service
public class UserManageFormServiceImpl implements UserManageFormService {

    private static final Logger logger = LoggerFactory.getLogger(UserManageFormServiceImpl.class);

    @Resource
    private UserManageFormRepository userManageFormRepository;

    @Override
    public List<UserManageFormRes> loadUserManageForm() {

        // 1.从数据库拉取数据
        List<UserManageVO> userManageVOS = userManageFormRepository.loadUserManageForm();

        // 2.遍历处理
        List<UserManageFormRes> userManageFormResList = new ArrayList<>();
        for (UserManageVO userManageVO : userManageVOS) {
            // 2.1 不展示ROOT用户
            if(userManageVO.getRole() == UserRoleEnum.ROOT.getLevel()){
                continue;
            }
            // 2.2 role转中文形式
            UserManageFormRes oneRes = new UserManageFormRes();
            oneRes.setUid(userManageVO.getUid());
            oneRes.setUsername(userManageVO.getUsername());
            String roleString = UserRoleEnum.castRoleToString(userManageVO.getRole());
            oneRes.setRole(roleString);
            // 2.3 添加到List中
            userManageFormResList.add(oneRes);
        }

        // 3.返回结果
        return userManageFormResList;
    }

    @Override
    public void deleteUser(int uid) {

        // 1.构造待删除集合
        List<Integer> uidList = new ArrayList<>();
        uidList.add(uid);

        // 2.执行删除
        int deleteCount = userManageFormRepository.deleteUsersById(uidList);

        // 3.删除数量异常
        if (deleteCount != 1) {
            logger.error("删除用户时出现异常");
            throw new ServiceException(ServiceExceptionEnum.DELETE_USER_ERROR);
        }
    }

    @Override
    public void batchDeleteUsers(List<Integer> uidList) {

        // 1.执行删除
        int deleteCount = userManageFormRepository.deleteUsersById(uidList);

        // 2.删除数量异常
        if(deleteCount != uidList.size()){
            logger.error("批量删除用户时出现异常");
            throw new ServiceException(ServiceExceptionEnum.DELETE_USER_ERROR);
        }
    }

    @Override
    public void editUserRole(int uid, String roleString) {

        // 1.构造VO
        UserRoleVO userRoleVO = new UserRoleVO();
        userRoleVO.setUid(uid);
        userRoleVO.setRole(UserRoleEnum.castRoleToInt(roleString));

        // 2.执行编辑
        int editCount = userManageFormRepository.editUserRole(userRoleVO);

        // 3.编辑数量异常
        if(editCount !=1){
            logger.error("编辑用户权限时出现异常");
            throw new ServiceException(ServiceExceptionEnum.EDIT_USER_ROLE_ERROR);
        }

    }

}
