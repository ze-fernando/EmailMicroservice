package com.ze_fernando.Microservices;

import com.ze_fernando.Microservices.controllers.EmailController;
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
import static org.mockito.ArgumentMatchers.any;
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
        EmailModel mail = new EmailModel();
        mail.setEmailFrom("test@example.com");
        mail.setEmailTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setBody("Test Text");
    
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailFrom(mail.getEmailFrom());
        emailModel.setEmailTo(mail.getEmailTo());
        emailModel.setSubject(mail.getSubject());
        emailModel.setBody(mail.getBody());
        emailModel.setStatus(MailStatus.DONE);
    
           doAnswer(invocation -> {
                EmailModel email = invocation.getArgument(0);
                email.setStatus(MailStatus.DONE); 
                return email;
            }).when(emailService)
            .sendEmail(any(EmailModel.class));
    
        ResponseEntity<Object> response = emailController.sendEmail(mail);
    
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(emailModel, response.getBody());
        
    }
    
    @Test
    void sendEmailControllerTestFailure() {
        EmailModel mail = new EmailModel();
        mail.setEmailFrom("test@example.com");
        mail.setEmailTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setBody("Test Text");

        when(emailService.sendEmail(mail)).thenThrow(new RuntimeException("Failed to send email"));
    
        ResponseEntity<Object> response = emailController.sendEmail(mail);
        RuntimeException re = new RuntimeException("Failed to send email");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: " + re, response.getBody());
        verify(emailService, times(1)).sendEmail(mail);
    }
    
}