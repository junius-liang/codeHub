package com.app.dao;

import cn.hutool.core.lang.Console;
import com.app.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author junius
 * @date 2023/04/29 19:01
 * @project codeHub
 **/
@SpringBootTest
class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    void getUserByUserName() {
        SysUser admin001 = userDao.getUserByUserName("admin001");
        assertNotNull(admin001);
    }

    @Test
    void getMenuByUserName() {
        List<String> userMenuList = userDao.getUserMenuList(1);
        Console.log(userMenuList);
        assertNotNull(userMenuList);
    }


    @Test
    void TestPwd(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin001 = passwordEncoder.encode("admin001");
        Console.log(admin001);
    }
}
