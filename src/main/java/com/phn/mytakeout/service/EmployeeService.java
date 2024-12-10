package com.phn.mytakeout.service;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyEmployeeDTO;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.vo.EmployeeVO;

import java.util.List;

public interface EmployeeService extends IService<Employee>{
    EmployeeVO login(LoginFormDTO loginFormDTO);

    PageResult search(EmployeeQueryDTO employeeQueryDTO);

    PageResult searchByMsg(EmployeeQueryDTO employeeQueryDTO);

    void deleteByIds(List<Long> ids);

    void modifyStatus(int status,Long id);

    void addEmployee(AddAndModifyEmployeeDTO addEmployeeDTO);

    Employee getEmployeeById(Long id);

    Result<Employee> modifyPassword(String newPassword);

    void modifyEmployee(AddAndModifyEmployeeDTO modifyEmployeeDTO);
}
