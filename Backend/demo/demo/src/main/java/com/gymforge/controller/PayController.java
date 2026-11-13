package com.gymforge.controller;

import com.gymforge.dto.request.CreatePayRequest;
import com.gymforge.dto.response.PayResponse;
import com.gymforge.service.PayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//----------------------------------------------------------------------------------------------//

@RestController
@RequestMapping("/api/pays")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @PostMapping
    public ResponseEntity<PayResponse> createPay(@Valid @RequestBody CreatePayRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(payService.createPay(request));
    }
}

//----------------------------------------------------------------------------------------------//