package com.app.service;

import com.app.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author junius
 * @date 2023/04/29 19:06
 * @project codeHub
 **/
public interface SysUserService {
    SysUser getUserByUserName(String userName);

    List<String> getUserMenuList( Integer userId);
}
