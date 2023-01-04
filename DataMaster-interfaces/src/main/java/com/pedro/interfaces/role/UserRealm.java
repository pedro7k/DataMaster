package com.pedro.interfaces.role;

import com.pedro.common.enums.UserRoleEnum;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.domain.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * UserRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法doGetAuthorizationInfo");

        // 1.为用户授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 2.拿到用户当前登陆的对象
        Subject subject = SecurityUtils.getSubject();
        UserVO currentUser = (UserVO) subject.getPrincipal();//拿到user对象

        // 3.添加role
        int roleKey = currentUser.getRole();
        for (String role : UserRoleEnum.getRoleString(roleKey)) {
            info.addRole(role);
        }

        return info;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("执行了认证方法doGetAuthenticationInfo");

        // 1.获取token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 2.连接真实数据库,通过token中的name获取User
        UserVO userVO = userService.queryUserByName(token.getUsername());
        if (userVO == null) {
            // 此人不存在，将自动处理为UnknownAccountException
            return null;
        }

        // 3.将user放入session中
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginUser", userVO);

        // 4.构造认证结果并返回，包含了正确的密码，可以自动做验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                userVO,
                userVO.getPassword(),
                ByteSource.Util.bytes(userVO.getUsername() + userVO.getSalt()), // 加密
                getName());
        return info;
    }
}
