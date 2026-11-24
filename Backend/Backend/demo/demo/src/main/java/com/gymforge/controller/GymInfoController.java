package com.gymforge.controller;

import com.gymforge.dto.request.UpdateGymInfoRequest;
import com.gymforge.dto.response.GymInfoResponse;
import com.gymforge.service.GymInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//----------------------------------------------------------------------//
// GymInfo controller (a terem alapadatai)

@RestController
@RequestMapping("/api/gym-info")
@RequiredArgsConstructor
public class GymInfoController {

    private final GymInfoService gymInfoService;

//---------------------------------------------------------------------------------------------------------//
//GetGymInfo
    @GetMapping
    public ResponseEntity<List<GymInfoResponse>> getAllGymInfo() {
        return ResponseEntity.ok(gymInfoService.getAllGymInfo());
    }
//---------------------------------------------------------------------------------------------------------//
//GetGymInfoByID KIKELL TÖRÖLNI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @GetMapping("/{id}")
    public ResponseEntity<GymInfoResponse> getGymInfoById(@PathVariable Integer id) {
        return ResponseEntity.ok(gymInfoService.getGymInfoById(id));
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//---------------------------------------------------------------------------------------------------------//
//UpdateGymInfo MEGNÉZNI, HOGY KELL-E ID. SZERINTEM NEM!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @PutMapping("/{id}")
    public ResponseEntity<GymInfoResponse> updateGymInfo(@PathVariable Integer id,
                                                         @Valid @RequestBody UpdateGymInfoRequest request) {
        return ResponseEntity.ok(gymInfoService.updateGymInfo(id, request));
    }
}

//----------------------------------------------------------------------//