package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.constant.OrderStatus;
import com.phn.mytakeout.domain.dto.OrderCancelDTO;
import com.phn.mytakeout.domain.dto.OrderPageQueryDTO;
import com.phn.mytakeout.domain.dto.OrderRejectionDTO;
import com.phn.mytakeout.domain.po.OrderDetail;
import com.phn.mytakeout.domain.po.Orders;
import com.phn.mytakeout.domain.vo.OrderStatusNumberVO;
import com.phn.mytakeout.domain.vo.OrderVO;
import com.phn.mytakeout.mapper.OrderDetailMapper;
import com.phn.mytakeout.mapper.OrderMapper;
import com.phn.mytakeout.service.OrderDetailService;
import com.phn.mytakeout.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    private final OrderDetailMapper orderMapper;

    private final OrderDetailService orderDetailService;

    @Override
    public PageResult search(OrderPageQueryDTO orderQueryDTO) {
        Page<Orders> page = new Page<>(orderQueryDTO.getPage(), orderQueryDTO.getPageSize());

        Page<Orders> result = lambdaQuery()
                .page(page);

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public PageResult getInfoByPage(OrderPageQueryDTO orderPageQueryDTO) {
        PageResult pageResult = new PageResult();
        Page<Orders> page = new Page<>(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());

        Page<Orders> ordersPage = lambdaQuery()
                .eq(orderPageQueryDTO.getStatus() != null,Orders::getStatus,orderPageQueryDTO.getStatus())
                .eq(orderPageQueryDTO.getNumber() != null,Orders::getNumber,orderPageQueryDTO.getNumber())
                .eq(orderPageQueryDTO.getPhone() != null,Orders::getPhone,orderPageQueryDTO.getPhone())
                .lt(orderPageQueryDTO.getEndTime() != null ,Orders::getOrderTime,orderPageQueryDTO.getEndTime())
                .gt(orderPageQueryDTO.getStartTime() != null ,Orders::getOrderTime,orderPageQueryDTO.getStartTime())
                .page(page);
        List<OrderVO> orderVO = new ArrayList<>();
        for(Orders orders : ordersPage.getRecords()){
            OrderVO VO = new OrderVO();
            BeanUtils.copyProperties(orders,VO);
            List<OrderDetail> orderDetailByOrderId = orderDetailService.lambdaQuery().eq(OrderDetail::getOrderId,orders.getId()).list();

            String DishName = "";
            for(OrderDetail orderDetail : orderDetailByOrderId){
                DishName = DishName + orderDetail.getName();
            }
            BeanUtils.copyProperties(orders,VO);
            VO.setOrderDishes(DishName);
            VO.setOrderDetailList(orderDetailByOrderId);
            orderVO.add(VO);
        }

        pageResult.setTotal(ordersPage.getTotal());
        pageResult.setRecord(orderVO);
        return pageResult;
    }

    @Override
    public OrderStatusNumberVO getStatusNumber() {
        OrderStatusNumberVO orderStatusNumberVO = new OrderStatusNumberVO();
        orderStatusNumberVO.setToBeConfirmed(lambdaQuery().eq(Orders::getStatus, OrderStatus.ToBeConfirmed).list().size());
        orderStatusNumberVO.setConfirmed(lambdaQuery().eq(Orders::getStatus, OrderStatus.Confirmed).list().size());
        orderStatusNumberVO.setDeliveryInProgress(lambdaQuery().eq(Orders::getStatus, OrderStatus.DeliveryInProgress).list().size());
        return orderStatusNumberVO;
    }

    @Override
    public void confirm(Long id) {
        lambdaUpdate().set(Orders::getStatus,OrderStatus.Confirmed).eq(Orders::getId,id).update();
    }

    @Override
    public void delivery(Long id) {
        lambdaUpdate().set(Orders::getStatus,OrderStatus.DeliveryInProgress).eq(Orders::getId,id).update();
    }

    @Override
    public void complete(Long id) {
        lambdaUpdate().set(Orders::getStatus,OrderStatus.completion).eq(Orders::getId,id).update();
    }

    @Override
    public void rejectionAndSetReason(OrderRejectionDTO ordersRejectionDTO) {
        lambdaUpdate().set(Orders::getStatus,OrderStatus.Canceled).set(Orders::getRejectionReason,ordersRejectionDTO.getRejectionReason()).eq(Orders::getId,ordersRejectionDTO.getId()).update();
    }

    @Override
    public void cancelAndSetReason(OrderCancelDTO ordersCancelDTO) {
        Orders one = lambdaQuery().eq(Orders::getId, ordersCancelDTO.getId()).one();
        lambdaUpdate().set(Orders::getStatus,one.getStatus()-1).set(Orders::getRejectionReason,ordersCancelDTO.getCancelReason()).eq(Orders::getId,ordersCancelDTO.getId()).update();
    }

    @Override
    public OrderVO getOneById(Long id) {

        OrderVO orderVO = new OrderVO();

        Orders one = lambdaQuery().eq(Orders::getId, id).one();
        BeanUtils.copyProperties(one,orderVO);
        List<OrderDetail> list = orderDetailService.lambdaQuery().eq(OrderDetail::getOrderId, id).list();
        orderVO.setOrderDetailList(list);
        return orderVO;

    }
}
