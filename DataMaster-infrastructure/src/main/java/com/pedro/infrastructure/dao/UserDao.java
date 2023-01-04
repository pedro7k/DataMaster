package com.pedro.infrastructure.dao;

import com.pedro.domain.user.model.vo.UserAuthVO;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    UserVO queryUserByName(String username);

    boolean registry(UserVO userVO);
}
