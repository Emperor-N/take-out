package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.OrderPageQueryDTO;
import com.phn.mytakeout.domain.po.Orders;
import com.phn.mytakeout.domain.vo.OrderStatusNumberVO;

public interface OrderService extends IService<Orders> {
    PageResult search(OrderPageQueryDTO orderQueryDTO);

    PageResult getInfoByPage(OrderPageQueryDTO ordersPageQueryDTO);

    OrderStatusNumberVO getStatusNumber();

    void confirm(Long id);

    void delivery(Long id);

    void complete(Long id);
}
