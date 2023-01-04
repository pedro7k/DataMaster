package com.pedro.infrastructure.repository;

import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.domain.user.repository.UserRepository;
import com.pedro.infrastructure.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDao userDao;

    @Override
    public UserVO queryUserByName(String username) {
        // 1.执行查询
        UserVO userVO = userDao.queryUserByName(username);

        // 2.若未查询到，则返回null
        if (userVO == null) {
            return null;
        }

        // 3.返回
        return userVO;
    }

    @Override
    public boolean registry(UserVO userVO) {
        return userDao.registry(userVO);
    }
}
