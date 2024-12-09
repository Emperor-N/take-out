package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.OrderQueryDTO;
import com.phn.mytakeout.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class orderController {

    private final orderService orderService;

    @GetMapping("/page")
    public Result<PageResult> getEmployeePage(OrderQueryDTO orderQueryDTO){
        return Result.success(orderService.search(orderQueryDTO));
    }

}
