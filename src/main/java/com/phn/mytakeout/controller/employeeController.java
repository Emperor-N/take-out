package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.vo.employeeVo;
import com.phn.mytakeout.service.employeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class employeeController {

    private final employeeService employeeService;

    @PostMapping("/employee/login")
    public Result<employeeVo> login(@RequestBody LoginFormDTO loginFormDTO){
        return Result.success(employeeService.login(loginFormDTO));
    }

}
