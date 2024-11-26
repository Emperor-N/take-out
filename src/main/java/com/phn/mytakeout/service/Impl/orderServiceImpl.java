package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.Orders;
import com.phn.mytakeout.mapper.orderMapper;
import com.phn.mytakeout.service.orderService;
import org.springframework.stereotype.Service;

@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, Orders> implements orderService {
}
