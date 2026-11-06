package com.gymforge.controller;

import com.gymforge.dto.request.CreateAdminRequest;
import com.gymforge.dto.request.UpdateAdminRequest;
import com.gymforge.dto.response.AdminResponse;
import com.gymforge.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponse> createAdmin(@Valid @RequestBody CreateAdminRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(request));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdateAdminRequest request) {
        return ResponseEntity.ok(adminService.updateAdmin(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.softDeleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}

//---------------------------------------------------------------------------------------------------------//