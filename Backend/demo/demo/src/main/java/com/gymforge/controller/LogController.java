package com.gymforge.controller;

import com.gymforge.dto.response.LogResponse;
import com.gymforge.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping
    public ResponseEntity<List<LogResponse>> getAllLogs() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogById(@PathVariable Integer id) {
        return ResponseEntity.ok(logService.getLogById(id));
    }

    @PostMapping
    public ResponseEntity<String> createLog(@RequestBody Map<String, Object> body) {
        logService.createLog(
                (Integer) body.get("userId"),
                (Integer) body.get("adminId"),
                (String) body.get("action"),
                (String) body.get("entityTable"),
                (Integer) body.get("entityId"));
        return ResponseEntity.status(HttpStatus.CREATED).body("Naplóbejegyzés létrehozva");
    }
}

//----------------------------------------------------------------------------------------------------//