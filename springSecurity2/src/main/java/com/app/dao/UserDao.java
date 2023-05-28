package com.app.dao;

import com.app.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author junius
 * @date 2023/04/29 18:38
 * @project codeHub
 **/
@Mapper
public interface UserDao {
    SysUser getUserByUserName(@Param("userName") String userName);

    List<String> getUserMenuList(@Param("userId") Integer userId);


}
