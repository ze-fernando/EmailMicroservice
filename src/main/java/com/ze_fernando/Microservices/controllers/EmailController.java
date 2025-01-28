package com.ze_fernando.Microservices.controllers;

import com.ze_fernando.Microservices.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto){
        try {
            var emailModel = new EmailModel();
            BeanUtils.copyProperties(emailDto, emailModel);
            emailService.sendEmail(emailModel);

            return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>("Failed to send email: " + re, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
