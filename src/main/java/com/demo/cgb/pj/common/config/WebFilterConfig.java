package com.demo.cgb.pj.common.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class WebFilterConfig {
    //注册filter对象

    public FilterRegistrationBean getFilterRegistrationBean(){
        //1.构建过滤器的注册对象
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //2.注册过滤器对象
        DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactory");

        filterRegistrationBean.setFilter(filter);

        //3.进行过滤器配置
        //暂时不知道怎么配置

        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

}
