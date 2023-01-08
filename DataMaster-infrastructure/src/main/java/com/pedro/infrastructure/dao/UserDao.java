package com.pedro.infrastructure.dao;

import com.pedro.domain.form.model.vo.UserRoleVO;
import com.pedro.domain.user.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Dao
 */
@Mapper
public interface UserDao {

    /**
     * 通过用户名查找User
     * @param username
     * @return
     */
    UserVO queryUserByName(String username);

    /**
     * 注册入库
     * @param userVO
     * @return
     */
    boolean registry(UserVO userVO);

    /**
     * 查询所有用户信息
     */
    List<UserVO> queryUserInfo();

    /**
     * 删除用户
     */
    boolean deleteUser(int uid);

    /**
     * 更新用户权限
     */
    int updateUserRole(UserRoleVO userRoleVO);
}
