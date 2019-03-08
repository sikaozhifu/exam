package com.school.config;

import com.school.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/page/login")//首页页面
                .excludePathPatterns("/static/**")//静态资源
                .excludePathPatterns("/page/register")//注册页面
                .excludePathPatterns("/user/login")//登录
                .excludePathPatterns("/user/register")//注册
                .excludePathPatterns("/admin/login")//管理员登录
                .excludePathPatterns("/page/adminForgot")
                .excludePathPatterns("/page/adminLogin");//管理员页面
        super.addInterceptors(registry);
    }
}
