package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junius
 * @date 2023/04/23 01:16
 * @project codeHub
 **/
@RestController
public class TeacherController {
    @GetMapping("/teacher/get")
    public String teacherList() {
        return "teacher page";
    }
}
