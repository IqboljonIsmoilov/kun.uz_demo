package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id;

    @Size(min = 10, max = 100, message = "few or lot")
    private String content;

    private Integer profileId;
    private Integer articleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
