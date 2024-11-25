package com.phn.mytakeout.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeQueryDTO {
    String username;
    Long page;
    Long pageSize;
}
