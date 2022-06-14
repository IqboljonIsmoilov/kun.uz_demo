package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO extends BestTimeAndIdDTO {

    private Integer id;

    @NotBlank(message = "not name")
    private String name;

    @NotBlank(message = "not surname")
    private String surname;

    @NotBlank(message = "not email")
    private String email;

    @NotBlank(message = "not password")
    private String password;

    private ProfileRole role;
    private ProfileStatus active;
    private String imageId;
    private String jwt;
    private AttachDTO attach;
}