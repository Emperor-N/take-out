package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class ModifyEmployeeDTO {
    Long id;
    String username;
    Integer gender;
    String number;
    String idNumber;
    String image;
}
