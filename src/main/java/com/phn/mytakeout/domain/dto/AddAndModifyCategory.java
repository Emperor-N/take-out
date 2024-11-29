package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class AddAndModifyCategory {
    private String name;
    private String description;
    private Integer type;
    private String image;

}
