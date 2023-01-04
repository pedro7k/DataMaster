package com.pedro.infrastructure.repository;

import com.pedro.domain.user.model.vo.UserAuthVO;
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
    public boolean checkUsername(String username) {
        return userDao.queryUserByName(username) != null;
    }

    @Override
    public boolean registry(UserVO userVO) {
        return userDao.registry(userVO);
    }
}
