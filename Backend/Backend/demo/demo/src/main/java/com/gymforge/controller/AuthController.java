package com.gymforge.controller;

import com.gymforge.dto.request.LoginRequest;
import com.gymforge.dto.response.LoginResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.User;
import com.gymforge.repository.UserRepository;
import com.gymforge.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//----------------------------------------------------------------------//
// AuthController (EGYELŐRE csak teszt: látjuk, hogy a token generálás működik)

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test-token")
    public String testToken() {
        return jwtUtil.generateToken(1, "teszt@teszt.hu", "USER");
    }


@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

    User user = userRepository.GetUserByEmailorUsername(request.getIdentifier())
            .orElseThrow(() -> new ResourceNotFoundException("Hibás email/felhasználó név vagy jelszó"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
        throw new ResourceNotFoundException("Hibás email/felhasználónév vagy jelszó");
    }

    String token = jwtUtil.generateToken(user.getId(), user.getEmail(), "USER");

    return ResponseEntity.ok(new LoginResponse(token));

}}
//----------------------------------------------------------------------//
