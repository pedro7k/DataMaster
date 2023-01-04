package com.pedro.domain.user.repository;

import com.pedro.domain.user.model.vo.UserAuthVO;
import com.pedro.domain.user.model.vo.UserVO;

/**
 * User仓储服务
 */
public interface UserRepository {

    UserVO queryUserByName(String username);

    boolean registry(UserVO userVO);

}
