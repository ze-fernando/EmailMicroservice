package com.ze_fernando.Microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ze_fernando.Microservices.enums.MailStatus;
import com.ze_fernando.Microservices.models.EmailModel;
import com.ze_fernando.Microservices.repositories.EmailRepository;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;
    
    @Autowired
    private JavaMailSender emailSender;

    @SuppressWarnings("finally")
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDate(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getBody());
            emailSender.send(message);

            emailModel.setStatus(MailStatus.DONE);
        } catch (MailException me) {
            emailModel.setStatus(MailStatus.ERROR);
            throw new RuntimeException("Error while sending email " + me);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
}