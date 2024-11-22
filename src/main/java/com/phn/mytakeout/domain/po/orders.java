package com.phn.mytakeout.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class orders {
    private int id;

    private String number;//订单号

    private int status;//订单状态：1待付款、2待接单、3已接单、4派送中、5已完成、6已取消、7退款

    private int userId;//下单用户

    private int addressBookId;//地址id

    private LocalDateTime orderTime;//下单时间

    private LocalDateTime CheckoutTime;//结账时间

    private int payMethod;//支付方式

    private int payStatus;//支付状态

    private int amount;//实收金额

    private String remark;//备注

    public String phone;//手机号

    private String address;//地址

    private String userName;//用户名称

    private String consignee;//收货人

    private String cancelReason;//取消原因

    private LocalDateTime cancelTime;//取消时间

    private LocalDateTime estimatedDeliveryTime;//预计送达时间

    private int deliveryStatus;//配送状态：1立即送出、2选择配送时间

    private LocalDateTime deliveryTime;//送达时间

    private int packAmount;//打包费

    private int tablewareNumber;//餐具数量

    private int tablewareStatus;//餐具数量状态：1按餐量提供、2选择具体数量
}
