package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junius
 * @date 2023/04/23 01:14
 * @project codeHub
 **/
@RestController
public class StudentController {
    @GetMapping("/student/get")
    public String studentList() {
        return "student page";
    }
}
