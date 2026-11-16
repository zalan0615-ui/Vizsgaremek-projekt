package com.gymforge.service;

import com.gymforge.dto.request.CreateContactMessageRequest;
import com.gymforge.dto.response.ContactMessagesResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.ContactMessages;
import com.gymforge.repository.ContactMessagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------//

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactMessagesService {

    private final ContactMessagesRepository contactMessagesRepository;
    private final EmailService emailService;

    @Value("${app.mail.recipient}")
    private String mailRecipient;

    //--------------------------------------------------------------------------
    // Létrehozás (createcontact_messages eljárás)

    @Transactional
    public ContactMessagesResponse createContactMessage(CreateContactMessageRequest request) {

        contactMessagesRepository.createContactMessage(
                request.getName(),
                request.getEmail(),
                request.getMessage(),
                "new",      // status
                null,       // resolved_at
                null        // admin_id
        );

        // Az eljárás nem ad vissza ID-t, ezért a legutóbbi egyező sort keressük meg
        ContactMessages saved = contactMessagesRepository.getAllContactMessages().stream()
                .filter(c -> c.getEmail().equals(request.getEmail())
                        && c.getName().equals(request.getName())
                        && c.getMessage().equals(request.getMessage()))
                .max(Comparator.comparing(ContactMessages::getId))
                .orElseThrow(() -> new ResourceNotFoundException("Az üzenet mentése nem sikerült"));

        sendNotificationEmail(saved);

        return toResponse(saved);
    }

    //--------------------------------------------------------------------------
    // Lekérdezés (getcontact_messages, getByIdcontact_messages eljárás)

    public List<ContactMessagesResponse> getAllContactMessages() {
        return contactMessagesRepository.getAllContactMessages()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ContactMessagesResponse getContactMessageById(Integer id) {
        return toResponse(findContactMessage(id));
    }

    //--------------------------------------------------------------------------
    // Módosítás (updatecontact_messages eljárás)

    @Transactional
    public ContactMessagesResponse updateContactMessage(Integer id, CreateContactMessageRequest request) {
        ContactMessages existing = findContactMessage(id);

        contactMessagesRepository.updateContactMessage(
                id,
                request.getName(),
                request.getEmail(),
                request.getMessage(),
                existing.getStatus().name().toLowerCase(),
                existing.getResolvedAt(),
                existing.getAdminId()
        );

        return toResponse(findContactMessage(id));
    }

    //--------------------------------------------------------------------------
    // Törlés



    //--------------------------------------------------------------------------
    // Segédmetódusok

    private void sendNotificationEmail(ContactMessages contact) {
        try {
            String subject = "Új kapcsolatfelvételi üzenet - " + contact.getName();
            String text = "Új üzenet érkezett a weboldalról.\n\n"
                    + "Név: " + contact.getName() + "\n"
                    + "Email: " + contact.getEmail() + "\n\n"
                    + "Üzenet:\n" + contact.getMessage();

            emailService.sendEmail(mailRecipient, subject, text);
        } catch (Exception e) {
            log.error("Az email küldése nem sikerült", e);
        }
    }

    private ContactMessages findContactMessage(Integer id) {
        return contactMessagesRepository.getContactMessageById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Az üzenet nem található"));
    }

    private ContactMessagesResponse toResponse(ContactMessages contact) {
        return ContactMessagesResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .message(contact.getMessage())
                .status(contact.getStatus())
                .createdAt(contact.getCreatedAt())
                .resolvedAt(contact.getResolvedAt())
                .adminId(contact.getAdminId())
                .build();
    }
}

//----------------------------------------------------------------------------------------------------//