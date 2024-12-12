package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;

import com.phn.mytakeout.domain.dto.OrderCancelDTO;
import com.phn.mytakeout.domain.dto.OrderChanceStatusDTO;
import com.phn.mytakeout.domain.dto.OrderPageQueryDTO;
import com.phn.mytakeout.domain.dto.OrderRejectionDTO;
import com.phn.mytakeout.domain.vo.OrderStatusNumberVO;
import com.phn.mytakeout.domain.vo.OrderVO;
import com.phn.mytakeout.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/page")
    public Result<PageResult> getEmployeePage(OrderPageQueryDTO orderQueryDTO){
        return Result.success(orderService.search(orderQueryDTO));
    }

    @PostMapping("/conditionSearch")
    public Result<PageResult> getInfoByPage(@RequestBody OrderPageQueryDTO ordersPageQueryDTO){
        PageResult pageResult= orderService.getInfoByPage(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/statistics")
    public Result<OrderStatusNumberVO> getStatusNumber(){
        return Result.success(orderService.getStatusNumber());
    }

    @PutMapping("/confirm")
    public Result confirm(@RequestBody OrderChanceStatusDTO orderChanceStatusDTO){
        orderService.confirm(orderChanceStatusDTO.getId());
        return Result.success();
    }

    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable Long id){
        orderService.delivery(id);
        return Result.success();
    }

    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable Long id){
        orderService.complete(id);
        return Result.success();
    }

    @PutMapping("/rejection")
    public Result rejection(@RequestBody OrderRejectionDTO ordersRejectionDTO) {
        orderService.rejectionAndSetReason(ordersRejectionDTO);
        return Result.success();
    }

    @PutMapping("/cancel")
    public Result cancel(@RequestBody OrderCancelDTO ordersCancelDTO){
        orderService.cancelAndSetReason(ordersCancelDTO);
        return Result.success();
    }

    @GetMapping("/details/{id}")
    public Result<OrderVO> getOneById(@PathVariable Long id){
        return Result.success(orderService.getOneById(id));
    }

}
