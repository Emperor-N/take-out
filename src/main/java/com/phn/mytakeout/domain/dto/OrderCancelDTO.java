package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class OrderCancelDTO {
    private Long id;

    private String cancelReason;
}
