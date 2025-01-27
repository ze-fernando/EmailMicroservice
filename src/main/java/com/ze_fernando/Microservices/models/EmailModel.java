package com.ze_fernando.Microservices.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ze_fernando.Microservices.enums.MailStatus;

import com.ms.email.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;

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