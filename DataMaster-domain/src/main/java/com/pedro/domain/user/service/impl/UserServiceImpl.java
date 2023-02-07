package com.pedro.domain.user.service.impl;

import com.pedro.common.config.Constants;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.domain.support.encryption.EncryptionUtil;
import com.pedro.domain.user.model.req.RegistryReq;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.domain.user.repository.UserRepository;
import com.pedro.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void registry(RegistryReq registryReq) {
        // 1.用户名校验
        if (checkUsername(registryReq.getUsername())) {
            throw new ServiceException(ServiceExceptionEnum.DUPLICATE_USERNAME);
        }

        // 2.密码校验
        if (!registryReq.getPassword().equals(registryReq.getRePassword())) {
            throw new ServiceException(ServiceExceptionEnum.REPEAT_PASSWORD_ERROR);
        }

        // 3.注册
        UserVO userVO = new UserVO();
        userVO.setUsername(registryReq.getUsername());
        userVO.setSalt(EncryptionUtil.getSalt());
        userVO.setPassword(EncryptionUtil.encryptPassword(registryReq.getUsername(), registryReq.getPassword(), userVO.getSalt()));
        userVO.setRole(UserRoleEnum.NORMAL_USER.getLevel());
        boolean success = userRepository.registry(userVO);

        // 4.注册失败
        if (!success) {
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }
    }

    @Override
    public UserVO queryUserByName(String username) {
        return userRepository.queryUserByName(username);
    }

    /**
     * 检查当前数据库内是否有同名User
     *
     * @param username
     * @return
     */
    private boolean checkUsername(String username) {
        if (userRepository.queryUserByName(username) != null) {
            return true;
        }
        return false;
    }
}
