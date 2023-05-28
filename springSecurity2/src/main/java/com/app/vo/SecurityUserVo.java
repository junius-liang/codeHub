package com.app.vo;

import com.app.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author junius
 * @date 2023/04/29 19:10
 * @project codeHub
 **/
public class SecurityUserVo implements UserDetails {

    private  SysUser sysUser;
    private List<String> authorities;

    public SecurityUserVo(SysUser sysUser,List<String> authorities) {
        this.sysUser = sysUser;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>();
        authorities.forEach(authority -> {
           GrantedAuthority grantedAuthority = () -> authority;
           result.add(grantedAuthority);
        });
        return result;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return sysUser.getAccountNotExpired()==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.getAccountNotLocked()==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return sysUser.getCredentialsNoExpired()==1;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getEnabled()==1;
    }
}
