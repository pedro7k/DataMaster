package com.pedro.interfaces.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro核心配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        // 1. 创建DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 2. 关联realme
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager
                                                                    defaultWebSecurityManager) {
        // 1.获取bean，设置安全管理器
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        // 2.过滤器配置
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 登录和注册页
        filterMap.put("/jumpToLoginPage", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/registry", "anon");
        // 权限控制
        filterMap.put("/**","authc");

//        //动态权限注入
//        List<Map<String,String>> perms = permsMap.getPerms();
//        perms.forEach(perm->filterMap.put(perm.get("url"),perm.get("permission")));

        // 3.向过滤器中添加map
        bean.setFilterChainDefinitionMap(filterMap);

        // 4.设置登陆的请求
        bean.setLoginUrl("/jumpToLoginPage");

//        // 5.设置未授权的请求
//        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

//    //密码加密算法设置
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //设置加密方式
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //设置散列的次数
//        hashedCredentialsMatcher.setHashIterations(2);
//        return hashedCredentialsMatcher;
//    }


}

