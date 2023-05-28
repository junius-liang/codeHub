package com.app.entity;

/**
 * @author junius
 * @date 2023/05/01 17:43
 * @project codeHub
 **/
public class SysMenu {
    private Integer id;
    private Integer pid;
    private Integer type;
    private String name;
    private String code;

    public SysMenu() {
    }

    public SysMenu(Integer id, Integer pid, Integer type, String name, String code) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
