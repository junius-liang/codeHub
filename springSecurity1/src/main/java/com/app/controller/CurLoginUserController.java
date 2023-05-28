package com.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author junius
 * @date 2023/04/24 15:32
 * @project codeHub
 **/
@RestController
public class CurLoginUserController {
    @GetMapping("/getlog1")
    public Authentication getAuthentication1(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/getlog2")
    public Principal getAuthentication2(Principal principal) {
        return principal;
    }

    @GetMapping("/getlog3")
    public Authentication getAuthentication3() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
