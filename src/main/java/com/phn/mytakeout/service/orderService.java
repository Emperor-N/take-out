package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.OrderQueryDTO;
import com.phn.mytakeout.domain.po.Orders;

public interface orderService extends IService<Orders> {
    PageResult search(OrderQueryDTO orderQueryDTO);
}
