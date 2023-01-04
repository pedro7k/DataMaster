package com.pedro.domain.user.repository;

import com.pedro.domain.user.model.vo.UserVO;

/**
 * User仓储服务
 */
public interface UserRepository {

    /**
     * 通过名字查询User
     * @param username
     * @return
     */
    UserVO queryUserByName(String username);

    /**
     * 注册
     * @param userVO
     * @return
     */
    boolean registry(UserVO userVO);

}
