package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.UserManageVO;
import com.pedro.domain.form.repository.UserManageFormRepository;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.infrastructure.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserManageFormRepositoryImpl implements UserManageFormRepository {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserManageVO> loadUserManageForm() {
        // 1.查询用户信息
        List<UserVO> userVOS = userDao.queryUserInfo();

        // 2.转为结果对象
        List<UserManageVO> userManageVOList = new ArrayList<>();
        for (UserVO userVO : userVOS) {
            UserManageVO userManageVO = new UserManageVO();
            userManageVO.setUid(userVO.getUid());
            userManageVO.setUsername(userVO.getUsername());
            userManageVO.setRole(userVO.getRole());
            userManageVOList.add(userManageVO);
        }

        return userManageVOList;
    }

    @Override
    public int deleteUsersById(List<Integer> uidList) {

        // 1.删除计数器
        int deleteCount = 0;

        // 2.逐个执行
        for (Integer uid : uidList) {
            boolean isSuccess = userDao.deleteUser(uid);
            if (isSuccess){
                deleteCount++;
            }
        }

        // 3.返回结果
        return deleteCount;
    }
}
