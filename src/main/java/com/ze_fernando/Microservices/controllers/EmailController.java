package com.ze_fernando.Microservices.controllers;

import com.ze_fernando.Microservices.dtos.EmailDto;
import com.ze_fernando.Microservices.models.EmailModel;
import com.ze_fernando.Microservices.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<Object> sendEmail(@RequestBody @Validated EmailModel mail){
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(mail, emailModel);
        try {
            emailService.sendEmail(emailModel);

            return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            String error = "Error: " + re;
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
