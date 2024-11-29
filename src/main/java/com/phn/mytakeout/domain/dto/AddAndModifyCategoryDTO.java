package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class AddAndModifyCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Integer type;
    private String image;

}
