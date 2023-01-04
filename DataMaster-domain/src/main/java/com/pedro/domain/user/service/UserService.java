package com.pedro.domain.user.service;

import com.pedro.domain.user.model.req.RegistryReq;
import com.pedro.domain.user.model.vo.UserVO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 注册服务
     * @param registryReq
     */
    void registry(RegistryReq registryReq);

    /**
     * 通过名字获取用户信息
     * @param username
     * @return
     */
    UserVO queryUserByName(String username);
}
