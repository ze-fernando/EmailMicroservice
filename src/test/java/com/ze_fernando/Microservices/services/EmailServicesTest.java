package com.ze_fernando.Microservices.services;

import com.ze_fernando.Microservices.dtos.EmailDto;
import com.ze_fernando.Microservices.enums.MailStatus;
import com.ze_fernando.Microservices.models.EmailModel;
import com.ze_fernando.Microservices.repositories.EmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class EmailServicesTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmailSuccess() {
        EmailModel mail = new EmailModel();
        mail.setEmailFrom("test@domain.com");
        mail.setEmailTo("recipient@domain.com");
        mail.setSubject("Test Subject");
        mail.setBody("Test Body");

        EmailModel mailModel = new EmailModel();
        mailModel.setStatus(MailStatus.DONE);

        when(emailRepository.save(any(EmailModel.class))).thenReturn(mailModel);

        EmailModel mailModelResult = emailService.sendEmail(mail);


        assertEquals(MailStatus.DONE, mailModelResult.getStatus());
        verify(emailRepository, times(1)).save(any(EmailModel.class));
    }


    @Test
    void sendEmailFailure() {
        EmailDto mail = new EmailDto();
        mail.setEmailFrom("test@domain.com");
        mail.setEmailTo("recipient@domain.com");
        mail.setSubject("Test Subject");
        mail.setBody("Test Body");

        EmailModel mailModel = new EmailModel();
        mailModel.setStatus(MailStatus.ERROR);

        when(emailRepository.save(any(EmailModel.class))).thenReturn(mailModel);

        EmailModel result = emailService.sendEmail(mailModel);

        assertEquals(MailStatus.ERROR, result.getStatus());
        verify(emailRepository, times(1)).save(any(EmailModel.class));

    }
}