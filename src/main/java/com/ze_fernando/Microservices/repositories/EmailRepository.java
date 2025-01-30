package com.ze_fernando.Microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ze_fernando.Microservices.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {}