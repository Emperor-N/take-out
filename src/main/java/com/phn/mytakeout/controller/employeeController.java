package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyEmployeeDTO;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.dto.ModifyEmployeeDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.vo.employeeVo;
import com.phn.mytakeout.service.employeeService;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/employee")
public class employeeController {

    private final employeeService employeeService;

    @PostMapping("/login")
    public Result<employeeVo> login(@RequestBody LoginFormDTO loginFormDTO){
        return Result.success(employeeService.login(loginFormDTO));
    }

    @GetMapping("/password/{password}")
    public Result<Employee> modifyPassword(@PathVariable("password") String newPassword){
        return employeeService.modifyPassword(newPassword);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getEmployeePage(EmployeeQueryDTO employeeQueryDTO){
        return Result.success(employeeService.search(employeeQueryDTO));
    }

    @PostMapping("/search")
    public Result<PageResult> search(@RequestBody EmployeeQueryDTO employeeQueryDTO){
        return Result.success(employeeService.searchByMsg(employeeQueryDTO));
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Long> ids){
        employeeService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result modifyStatus(@PathVariable ("status") int status, @RequestParam Long id){
        employeeService.modifyStatus(status,id);
        return Result.success();
    }

    @PostMapping("")
    public Result addEmployee(@RequestBody AddAndModifyEmployeeDTO addEmployeeDTO){
        employeeService.addEmployee(addEmployeeDTO);
        return Result.success();
    }

    @PostMapping("/modify")
    public Result modifyEmployee(@RequestBody AddAndModifyEmployeeDTO modifyEmployeeDTO){
        employeeService.modifyEmployee(modifyEmployeeDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Employee> getEmployeeById(@PathVariable Long id){
        return Result.success(employeeService.getEmployeeById(id));
    }

}
