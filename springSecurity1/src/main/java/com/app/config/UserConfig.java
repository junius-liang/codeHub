package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author junius
 * @date 2023/04/24 14:18
 * @project codeHub
 * 用户权限认证配置
 **/

@Configuration
public class UserConfig {
    @Bean
   public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.builder().username("student001").password(passwordEncoder().encode("student001")).roles("student").build();
        UserDetails userDetails2 = User.builder().username("teacher002")
                .password(passwordEncoder().encode("teacher002")).roles("teacher")
                .build();
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(userDetails1);
        userDetailsManager.createUser(userDetails2);
        return userDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //无密码加密
//        return NoOpPasswordEncoder.getInstance();
        //加密
        return new BCryptPasswordEncoder();
    }
}

