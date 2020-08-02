package com.demo.cgb.pj.common.config;



//anonymous  匿名的

import com.demo.cgb.pj.sys.service.realm.ShiroUserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


/**
 * 1.配置SecurityManager对象
 * 2.配置SecurityManager的CacheManager属性
 * 3.配置SecurityManager的realm属性
 * 4.配置LifecycleBeanPostProcessor，可以用来调用配置在Spring IOC容器中shiro bean的生命周期方法
 */



@Configuration
public class SpringShiroConfig {


    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") ShiroUserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //5.配置shiroFilter
    @Bean("shiroFilterFactory")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //假如没有认证请求先访问此认证的url
        shiroFilterFactoryBean.setLoginUrl("/doLoginUI");

        //定义map指定请求过滤规则（哪些资源允许匿名访问）
        LinkedHashMap<String,String> map = new LinkedHashMap<>();

        //静态资源允许匿名访问:"anon"
        map.put("/bower_components/**","anon");
        map.put("/build/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        map.put("/user/doLogin","anon");
        map.put("/doLogout","logout");

        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean("userRealm")
    public ShiroUserRealm getRealm(){
        return new ShiroUserRealm();
    }
}

