package com.phn.mytakeout.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddAndModifyDishDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
}
