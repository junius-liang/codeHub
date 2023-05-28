package com.app.service.impl;

import com.app.dao.UserDao;
import com.app.entity.SysUser;
import com.app.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junius
 * @date 2023/04/29 19:08
 * @project codeHub
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private UserDao userDao;

    @Override
    public SysUser getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<String> getUserMenuList(Integer userId) {
        return userDao.getUserMenuList(userId);
    }
}
