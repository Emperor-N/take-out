package com.phn.mytakeout.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishQueryDTO {

    String name;
    Integer page;
    Integer pageSize;
    Integer status;
    Long CategoryId;
}
