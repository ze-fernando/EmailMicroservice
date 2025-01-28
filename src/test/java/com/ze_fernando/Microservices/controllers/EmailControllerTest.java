package com.ze_fernando.Microservices.controllers;

import com.ze_fernando.Microservices.dtos.EmailDto;
import com.ze_fernando.Microservices.enums.MailStatus;
import com.ze_fernando.Microservices.models.EmailModel;
import com.ze_fernando.Microservices.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class EmailControllerTest {
    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmailControllerTestSuccess() {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailFrom("test@example.com");
        emailDto.setEmailTo("recipient@example.com");
        emailDto.setSubject("Test Subject");
        emailDto.setText("Test Text");
    
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailFrom(emailDto.getEmailFrom());
        emailModel.setEmailTo(emailDto.getEmailTo());
        emailModel.setSubject(emailDto.getSubject());
        emailModel.setText(emailDto.getText());
        emailModel.setSendDateEmail(LocalDateTime.now());
        emailModel.setStatusEmail(MailStatus.SENT);
    
        when(emailService.sendEmail(emailDto)).thenReturn(emailModel);
    
        ResponseEntity<EmailModel> response = emailController.sendEmail(emailDto);
    
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(emailModel, response.getBody());
        verify(emailService, times(1)).sendEmail(emailDto);
    }
    
    @Test
    void testSendEmail_ReturnsErrorResponseOnFailure() {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailFrom("test@example.com");
        emailDto.setEmailTo("recipient@example.com");
        emailDto.setSubject("Test Subject");
        emailDto.setText("Test Text");
    
        when(emailService.sendEmail(emailDto)).thenThrow(new RuntimeException("Service failure"));
    
        ResponseEntity<Object> response = emailController.sendEmail(emailDto);
    
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to send email: ", response.getBody());
        verify(emailService, times(1)).sendEmail(emailDto);
    }
    
}