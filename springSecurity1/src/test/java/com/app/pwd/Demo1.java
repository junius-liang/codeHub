package com.app.pwd;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author junius
 * @date 2023/04/24 15:04
 * @project codeHub
 **/
public class Demo1 {
    @Test
    public void method1(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        String encode3 = passwordEncoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);
        boolean matches1 = passwordEncoder.matches("123456", encode1);
        boolean matches2 = passwordEncoder.matches("123456", encode2);
        boolean matches3 = passwordEncoder.matches("123456", encode3);
        System.out.println(matches1+"::"+matches2+"::"+matches3);
        /*
        $2a$10$zKsV6yuJJ/s5KrlaUS63ieLt3EUINOV2AUmug/foHjEpMfST6hLou
        $2a$10$HXznQQebNko6XLzni67DUONedVGKk7dyhVnyXdtONj4HCmWyX.bLi
        $2a$10$9XQ.LMvHiAYX6G1w1vy4S.SCN3RQPjm/XrgjBDCEec.jIPc5meuPW
        true::true::true
         */
    }
}
