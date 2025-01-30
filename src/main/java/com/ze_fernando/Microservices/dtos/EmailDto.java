package com.ze_fernando.Microservices.dtos;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDto {
    
    @Nonnull
    private String emailFrom;
    @Nonnull
    private String emailTo;
    @Nonnull
    private String subject;
    @Nonnull
    private String body;
}