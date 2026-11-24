package com.gymforge.controller;

import com.gymforge.dto.response.UserTokensResponse;
import com.gymforge.service.UserTokensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-tokens")
@RequiredArgsConstructor
public class UserTokensController {

    private final UserTokensService userTokensService;

//--------------------------------------------------------------------------------//
// CreateUserTokens
    @PostMapping("/{userId}")
    public ResponseEntity<UserTokensResponse> createRememberToken(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userTokensService.createRememberToken(userId));
    }

    //--------------------------------------------------------------------------------//
// GetUserTokens
    @GetMapping
    public ResponseEntity<List<UserTokensResponse>> getAllUserTokens() {
        return ResponseEntity.ok(userTokensService.getAllUserTokens());
    }

    //--------------------------------------------------------------------------------//
// GetUserTokensById
    @GetMapping("/{id}")
    public ResponseEntity<UserTokensResponse> getUserTokensById(@PathVariable Integer id) {
        return ResponseEntity.ok(userTokensService.getUserTokensById(id));
    }

    //--------------------------------------------------------------------------------//
// UpdateCreateUserTokens
    @PutMapping("/{id}")
    public ResponseEntity<UserTokensResponse> updateRememberToken(@PathVariable Integer id) {
        return ResponseEntity.ok(userTokensService.updateRememberToken(id));
    }
}