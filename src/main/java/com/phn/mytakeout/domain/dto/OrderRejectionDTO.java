package com.phn.mytakeout.domain.dto;

import lombok.Data;

@Data
public class OrderRejectionDTO {
    private Long id;

    private String rejectionReason;
}
