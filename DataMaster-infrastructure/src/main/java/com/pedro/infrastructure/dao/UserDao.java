package com.pedro.infrastructure.dao;

import com.pedro.domain.user.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

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
}
