package com.ze_fernando.Microservices.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ze_fernando.Microservices.enums.MailStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.Data;


@Data
@Entity
@Table(name = "mails")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition  = "TEXT")
    private String body;
    private LocalDateTime sendDate;
    private MailStatus status;
}