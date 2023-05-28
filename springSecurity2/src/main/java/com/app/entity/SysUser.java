package com.app.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author junius
 * @date 2023/04/29 18:32
 * @project codeHub
 **/
public class SysUser implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private String sex;
    private int enabled;
    private int accountNotExpired;
    private int credentialsNoExpired;
    private int accountNotLocked;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getAccountNotExpired() {
        return accountNotExpired;
    }

    public void setAccountNotExpired(int accountNotExpired) {
        this.accountNotExpired = accountNotExpired;
    }

    public int getCredentialsNoExpired() {
        return credentialsNoExpired;
    }

    public void setCredentialsNoExpired(int credentialsNoExpired) {
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public int getAccountNotLocked() {
        return accountNotLocked;
    }

    public void setAccountNotLocked(int accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public SysUser() {
    }

    public SysUser(int userId, String userName, String password, String sex, int enabled, int accountNotExpired, int credentialsNoExpired, int accountNotLocked) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.enabled = enabled;
        this.accountNotExpired = accountNotExpired;
        this.credentialsNoExpired = credentialsNoExpired;
        this.accountNotLocked = accountNotLocked;
    }
}
