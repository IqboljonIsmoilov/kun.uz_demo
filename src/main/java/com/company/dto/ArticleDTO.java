package com.company.dto;

import com.company.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {

    private Integer id;

    @NotBlank
    @Size(min = 10, max = 100, message = "few symbol or a lot symbol")
    private String title;
    @NotBlank
    @Size(min = 100, max = 1000, message = "few symbol or a lot symbol")
    private String description;
    @NotBlank
    private String content;

    private Integer profileId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private ArticleStatus status;
    private LocalDateTime publishedDate;

    private String attachId;

    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer regionId;
    @NotNull
    private Integer typeId;

    private Integer viewCount;
    private Integer sharedCount;

    private List<Integer> tagIdList;
    private List<TagDTO> tagDTOList;

    private AttachDTO image;
    private CategoryDTO category;
    private LikeDTO like;
    private RegionDTO region;
    private ArticleTypeDTO articleType;
}
