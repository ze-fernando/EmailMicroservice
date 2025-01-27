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
}