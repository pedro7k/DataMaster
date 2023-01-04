package com.pedro.domain.user.service;

import com.pedro.domain.user.model.req.RegistryReq;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 注册服务
     * @param registryReq
     */
    void registry(RegistryReq registryReq);
}
