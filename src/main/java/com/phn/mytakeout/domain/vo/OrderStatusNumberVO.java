package com.phn.mytakeout.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusNumberVO {
    private Integer toBeConfirmed;
    private Integer confirmed;
    private Integer deliveryInProgress;
}
