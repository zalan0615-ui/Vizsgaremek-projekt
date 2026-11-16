package com.gymforge.controller;

import com.gymforge.dto.request.CreateContactMessageRequest;
import com.gymforge.dto.response.ContactMessagesResponse;
import com.gymforge.service.ContactMessagesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactMessagesController {

    private final ContactMessagesService contactMessagesService;

    @PostMapping
    public ResponseEntity<ContactMessagesResponse> createContactMessage(@Valid @RequestBody CreateContactMessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactMessagesService.createContactMessage(request));
    }

    @GetMapping
    public ResponseEntity<List<ContactMessagesResponse>> getAllContactMessages() {
        return ResponseEntity.ok(contactMessagesService.getAllContactMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactMessagesResponse> getContactMessageById(@PathVariable Integer id) {
        return ResponseEntity.ok(contactMessagesService.getContactMessageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactMessagesResponse> updateContactMessage(@PathVariable Integer id,
                                                                        @Valid @RequestBody CreateContactMessageRequest request) {
        return ResponseEntity.ok(contactMessagesService.updateContactMessage(id, request));
    }


}