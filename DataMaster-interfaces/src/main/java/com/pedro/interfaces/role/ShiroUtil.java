package com.pedro.interfaces.role;

import com.pedro.domain.user.model.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * ShiroUtil
 */
public class ShiroUtil {

    /**
     * 工具方法，返回当前User信息
     * @return
     */
    public static UserVO getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        return (UserVO) subject.getPrincipal();
    }
}
