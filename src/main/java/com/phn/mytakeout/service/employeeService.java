package com.phn.mytakeout.service;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.vo.employeeVo;

import java.util.List;

public interface employeeService extends IService<Employee>{
    employeeVo login(LoginFormDTO loginFormDTO);

    PageResult search(EmployeeQueryDTO employeeQueryDTO);

    PageResult searchByMsg(EmployeeQueryDTO employeeQueryDTO);

    void deleteByIds(List<Long> ids);

    void modifyStatus(int status,int id);
}
