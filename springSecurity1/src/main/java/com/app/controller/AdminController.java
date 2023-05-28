package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junius
 * @date 2023/04/23 01:17
 * @project codeHub
 **/
@RestController
public class AdminController {
    @GetMapping("/admin/get")
    public String adminList() {
        return "admin page";
    }
}
