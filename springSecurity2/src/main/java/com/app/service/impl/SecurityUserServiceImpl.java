package com.app.service.impl;

import com.app.entity.SysUser;
import com.app.service.SecurityUserService;
import com.app.service.SysUserService;
import com.app.vo.SecurityUserVo;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junius
 * @date 2023/04/29 19:19
 * @project codeHub
 **/
@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userByUserName = sysUserService.getUserByUserName(username);
        if (null==userByUserName){
            throw new UsernameNotFoundException("user not found");
        }
        List<String> userMenuList = sysUserService.getUserMenuList(userByUserName.getUserId());

        return new SecurityUserVo(userByUserName,userMenuList);
    }
}
