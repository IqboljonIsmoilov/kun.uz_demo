package com.company.dto;

import com.company.enums.EmailType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDTO {

    private Integer id;

    @NotBlank(message = "not email")
    @Size(min = 20, max = 50, message = "........")
    private String toEmail;

    private EmailType type;
    private LocalDateTime sendDate = LocalDateTime.now();
}
