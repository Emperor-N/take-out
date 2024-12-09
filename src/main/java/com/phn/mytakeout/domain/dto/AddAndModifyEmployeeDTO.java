package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class AddAndModifyEmployeeDTO {
    private Long id;
    private String username;
    private Integer gender;
    private String number;
    private String idNumber;
    private String image;
}
