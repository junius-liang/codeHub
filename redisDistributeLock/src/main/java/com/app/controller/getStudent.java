package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junius
 * @date 2023/05/01 22:28
 * @project codeHub
 **/
@RestController
public class getStudent {
    @RequestMapping("/getStudent")
    public String get(){
        return "get.......................";
    }
}
