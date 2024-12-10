package com.phn.mytakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phn.mytakeout.domain.po.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    List<OrderDetail> getOrderDetailByOrderId(Long orderId);
}
