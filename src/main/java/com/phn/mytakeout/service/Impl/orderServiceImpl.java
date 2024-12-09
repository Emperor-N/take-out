package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.OrderQueryDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.po.Orders;
import com.phn.mytakeout.mapper.orderMapper;
import com.phn.mytakeout.service.orderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class orderServiceImpl extends ServiceImpl<orderMapper, Orders> implements orderService {
    @Override
    public PageResult search(OrderQueryDTO orderQueryDTO) {
        Page<Orders> page = new Page<>(orderQueryDTO.getPage(), orderQueryDTO.getPageSize());

        Page<Orders> result = lambdaQuery()
                .page(page);

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }
}
