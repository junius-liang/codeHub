package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

/**
 * @author junius
 * @date 2023/04/25 14:54
 * @project codeHub
 * 对url进行授权配置
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AppAuthenticationSuccessConfig appAuthenticationSuccessConfig;
    @Resource
    private AppAuthenticationFailConfig appAuthenticationFailureConfig;
    @Resource
    private AppLogoutConfig appLogoutConfig;
    @Resource
    private AppAccessDenyConfig appAccessDenyConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //给路径配置权限，上下方法等同
                .mvcMatchers("/student/**")
                .hasAuthority("ROLE_student")
                .mvcMatchers("/teacher/**")
                .hasRole("teacher")
                .mvcMatchers("/admin/**")
                .hasAnyAuthority("ROLE_teacher","ROLE_student")
                .anyRequest()
                .authenticated();
        http.formLogin()
                //配置认证成功处理器
                .successHandler(appAuthenticationSuccessConfig)
                //配置认证失败处理器
                .failureHandler(appAuthenticationFailureConfig)
                .permitAll();

        //配置推出成功处理器
        http.logout().logoutSuccessHandler(appLogoutConfig);
        //配置认证失败
        http.exceptionHandling().accessDeniedHandler(appAccessDenyConfig);
    }
}
