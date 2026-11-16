package com.gymforge.controller;

import com.gymforge.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//----------------------------------------------------------------------//
// AuthController (EGYELŐRE csak teszt: látjuk, hogy a token generálás működik)

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    // GET /api/auth/test-token -> kiír egy teszt tokent
    @GetMapping("/test-token")
    public String testToken() {
        return jwtUtil.generateToken(1, "teszt@teszt.hu", "USER");
    }
}

//----------------------------------------------------------------------//