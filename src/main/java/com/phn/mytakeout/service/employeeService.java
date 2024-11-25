package com.phn.mytakeout.service;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.vo.employeeVo;

public interface employeeService extends IService<employee>{
    employeeVo login(LoginFormDTO loginFormDTO);

    PageResult search(EmployeeQueryDTO employeeQueryDTO);
}
