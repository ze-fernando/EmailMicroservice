package com.ze_fernando.Microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ze_fernando.Microservices.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {}