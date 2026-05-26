package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordGenerator {
    @Test
    void generate() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // "password" 部分を、登録したい生のパスワードに変えて実行してください
        String hash = encoder.encode("password");
        System.out.println("Hashed Password: " + hash);
    }
}
