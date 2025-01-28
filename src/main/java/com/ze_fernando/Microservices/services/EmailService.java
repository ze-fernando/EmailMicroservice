package com.ze_fernando.Microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ze_fernando.Microservices.enums.MailStatus;
import com.ze_fernando.Microservices.models.EmailModel;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;
    
    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel email) {
        email.setSendDate(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setEmailFrom(emailEmail.getFrom());
            message.setEmailTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setBody(email.getBody());
            emailSender.send(message);

            email.setStatus(MailStatus.DONE);
        } catch (MailException me) {
            email.setStatus(MailStatus.Error);
            throw new RuntimeException("Error while sending email " + me);
        } finally {
            return emailRepository.save(email);
        }
    }
}