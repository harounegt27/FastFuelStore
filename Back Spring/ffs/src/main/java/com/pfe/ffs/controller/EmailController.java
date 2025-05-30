package com.pfe.ffs.controller;

import com.pfe.ffs.dto.EmailRequest;
import com.pfe.ffs.services.messagerie.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String messageBody = emailRequest.getMessageBody(); // Récupérez le corps du message depuis la requête
        emailService.sendEmail(to, subject, messageBody);
    }
}
