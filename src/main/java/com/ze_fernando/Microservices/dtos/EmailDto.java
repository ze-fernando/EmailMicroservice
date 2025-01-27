package ze_fernando.Microservices.dtos;

import lombok.Data;

@Data
public class EmailDto {

    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
}