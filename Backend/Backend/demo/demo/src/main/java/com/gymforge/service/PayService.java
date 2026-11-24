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

//-----------------------------------------------------------------------------
//CreatePay

    @Transactional
    public PayResponse createPay(CreatePayRequest request) {

        Integer id = payRepository.createPay(
                request.getUserId(),
                request.getMembershipId(),
                request.getPrice()
        );

        boolean sent = sendConfirmationEmail(request.getEmail()); //ÁTNÉZNI!!!
        if (sent) {
            payRepository.markEmailSent(id);
        }

        return toResponse(findPay(id));
    }

//-----------------------------------------------------------------------------
//GetAllPay
    public List<PayResponse> getAllPays() {
        return payRepository.getAllPays()
                .stream()
                .map(this::toResponse)
                .toList();
    }
//-----------------------------------------------------------------------------
//GetPayById
    public PayResponse getPayById(Integer id) {
        return toResponse(findPay(id));
    }



    private Pays findPay(Integer id) {
        return payRepository.getPayById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A fizetés nem található"));
    }

//-----------------------------------------------------------------------------
//PAY EMAIL FONTOS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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