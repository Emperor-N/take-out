package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class AddAndModifyEmployeeDTO {
    Long id;
    String username;
    Integer gender;
    String number;
    String idNumber;
    String image;
}
