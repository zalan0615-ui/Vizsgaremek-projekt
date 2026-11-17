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
    // POST /api/user-tokens/{userId} -> új remember_token generálása és mentése a usernek
    @PostMapping("/{userId}")
    public ResponseEntity<UserTokensResponse> createRememberToken(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userTokensService.createRememberToken(userId));
    }

    //--------------------------------------------------------------------------------//
    // GET /api/user-tokens -> összes token lekérése
    @GetMapping
    public ResponseEntity<List<UserTokensResponse>> getAllUserTokens() {
        return ResponseEntity.ok(userTokensService.getAllUserTokens());
    }

    //--------------------------------------------------------------------------------//
    // GET /api/user-tokens/{id} -> egy token lekérése id alapján
    @GetMapping("/{id}")
    public ResponseEntity<UserTokensResponse> getUserTokensById(@PathVariable Integer id) {
        return ResponseEntity.ok(userTokensService.getUserTokensById(id));
    }

    //--------------------------------------------------------------------------------//
    // PUT /api/user-tokens/{id} -> remember_token frissítése (újragenerálása)
    @PutMapping("/{id}")
    public ResponseEntity<UserTokensResponse> updateRememberToken(@PathVariable Integer id) {
        return ResponseEntity.ok(userTokensService.updateRememberToken(id));
    }
}