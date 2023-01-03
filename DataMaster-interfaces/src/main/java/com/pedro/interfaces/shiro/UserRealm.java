package com.pedro.interfaces.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * UserRealm
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法doGetAuthorizationInfo");

        //为用户授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        //拿到用户当前登陆的对象
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User) subject.getPrincipal();//拿到user对象
//
//        //添加角色
//        info.addRole(currentUser.getRole());
//        //添加权限
//        String perms = currentUser.getPerms();
//        String[] split = perms.split(";");
//        for (String s : split) {
//            info.addStringPermission(s);
//        }

        return info;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("执行了认证方法doGetAuthenticationInfo");

//        //获取token
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//
//        //连接真实数据库,通过token中的name
//        User user = userService.queryUserByName(token.getUsername());
//        if (user == null){//此人不存在
//            return null;//UnknownAccountException
//        }
//
//        //将user放入session中
//        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setAttribute("loginUser",user);
//
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
//                user,
//                user.getPassword(),
//                ByteSource.Util.bytes(user.getUsername()+user.getSalt()),
//                getName());
//
//        return info;

        return null;
    }
}
