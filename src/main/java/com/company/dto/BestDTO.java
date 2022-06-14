package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BestDTO extends BestTimeAndIdDTO {

    @NotNull
    private String nameRu;
    @NotNull
    private String nameEn;
    @NotNull
    private String nameUz;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotBlank
    private String key;
    private Integer pid;
}