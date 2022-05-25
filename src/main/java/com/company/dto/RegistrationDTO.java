package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Surname cannot be null")
    private String surname;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotBlank
    @Size(min = 6, max = 16, message = "few or lot")
    private String password;
}