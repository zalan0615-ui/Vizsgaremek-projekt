package com.gymforge.service;

import com.gymforge.dto.request.CreatePayRequest;
import com.gymforge.dto.response.PayResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Pays;
import com.gymforge.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//----------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
@Slf4j
public class PayService {

    private final PayRepository payRepository;
    private final EmailService emailService;

    //------------------------------------------------------------------------
    // Létrehozás (createpaysWithId eljárás) + visszaigazoló email

    @Transactional
    public PayResponse createPay(CreatePayRequest request) {

        Integer id = payRepository.createPay(
                request.getUserId(),         // user_id
                request.getMembershipId(),   // membership_id
                request.getPrice()           // price
        );

        // Email küldése, csak siker esetén lesz email_sent = 1
        boolean sent = sendConfirmationEmail(request.getEmail());
        if (sent) {
            payRepository.markEmailSent(id);
        }

        return toResponse(findPay(id));
    }

    //------------------------------------------------------------------------

    public List<PayResponse> getAllPays() {
        return payRepository.getAllPays()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PayResponse getPayById(Integer id) {
        return toResponse(findPay(id));
    }

    //------------------------------------------------------------------------
    // Segédmetódusok

    private Pays findPay(Integer id) {
        return payRepository.getPayById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A fizetés nem található"));
    }

    // Visszaigazoló email a vásárlónak
    private boolean sendConfirmationEmail(String to) {
        try {
            String subject = "Jegyfoglalás visszaigazolása";
            String text = "JEGY KIVÁLASZTVA\n\n"
                    + "A kiválasztott jegyet rögzítettük a rendszerben.\n\n"
                    + "FONTOS INFORMÁCIÓ\n"
                    + "Fizetés csak készpénzzel.\n"
                    + "A jegy árát a helyszínen, készpénzben tudod rendezni.\n\n"
                    + "GymForge";
            emailService.sendEmail(to, subject, text);
            return true;
        } catch (Exception e) {
            log.error("A visszaigazoló email küldése nem sikerült", e);
            return false;
        }
    }

    private PayResponse toResponse(Pays pay) {
        return PayResponse.builder()
                .id(pay.getId())
                .userId(pay.getUserId())
                .membershipId(pay.getMembershipId())
                .price(pay.getPrice())
                .status(pay.getStatus().name())
                .emailSent(pay.getEmailSent())
                .build();
    }
}

//----------------------------------------------------------------------------------------------//