package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyEmployeeDTO;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.vo.EmployeeVO;
import com.phn.mytakeout.mapper.employeeMapper;
import com.phn.mytakeout.properties.JwtProperties;
import com.phn.mytakeout.service.employeeService;
import com.phn.mytakeout.utils.JwtTool;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class employeeServiceImpl extends ServiceImpl<employeeMapper, Employee> implements employeeService {

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    private final employeeMapper employeeMapper;

    @Override
    public EmployeeVO login(LoginFormDTO loginFormDTO) {

        String userName = loginFormDTO.getUsername();
        String password = loginFormDTO.getPassword();

        //查询username的记录
        Employee emp = lambdaQuery().eq(Employee::getUsername, userName).one();

        if(emp.getStatus() == 0){//判断账号是否冻结
            //Todo
            return null;//抛出账号状态冻结异常
        }
        if(!emp.getPassword().equals(password)){//判断账号密码是否正确
            return null;//抛出账号异常
        }

        EmployeeVO vo = new EmployeeVO();

        //生成token
        String token = jwtTool.createJwt(jwtProperties.getSecret(), emp.getId(), jwtProperties.getTtl());
        vo.setToken(token);
        vo.setUserName(userName);
        vo.setPassword(password);
        return vo;
    }

    @Override
    public PageResult search(EmployeeQueryDTO employeeQueryDTO) {
        Page<Employee> page = new Page<>(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize());

        Page<Employee> result = lambdaQuery()
                .page(page);

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Employee employee:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(employee.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(employee.getUpdateUser());
            if(createMap.get(employee.getCreateUser()) != null){
                employee.setCreateUserName(createMap.get(employee.getCreateUser()));
            }else{
                createMap.put(employee.getCreateUser(), createUserInfo.getUsername());
                employee.setCreateUserName(createMap.get(employee.getCreateUser()));
            }

            if(updateMap.get(employee.getUpdateUser()) != null){
                employee.setUpdateUserName(createMap.get(employee.getUpdateUser()));
            }else{
                updateMap.put(employee.getUpdateUser(), updateUserInfo.getUsername());
                employee.setUpdateUserName(updateMap.get(employee.getUpdateUser()));
            }
        }

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public PageResult searchByMsg(EmployeeQueryDTO employeeQueryDTO) {
        Page<Employee> page = new Page<>(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize());

        // 创建查询条件并执行分页查询
        Page<Employee> result = lambdaQuery()
                .like(StringUtils.isNotBlank(employeeQueryDTO.getUsername()), Employee::getUsername, employeeQueryDTO.getUsername())
                .eq(employeeQueryDTO.getStatus() != null,Employee::getStatus,employeeQueryDTO.getStatus())
                .lt(employeeQueryDTO.getEndTime()!=null,Employee::getCreateTime,employeeQueryDTO.getEndTime())
                .gt(employeeQueryDTO.getStartTime()!=null,Employee::getCreateTime,employeeQueryDTO.getStartTime())
                .page(page);
        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Employee employee:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(employee.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(employee.getUpdateUser());
            if(createMap.get(employee.getCreateUser()) != null){
                employee.setCreateUserName(createMap.get(employee.getCreateUser()));
            }else{
                createMap.put(employee.getCreateUser(), createUserInfo.getUsername());
                employee.setCreateUserName(createMap.get(employee.getCreateUser()));
            }

            if(updateMap.get(employee.getUpdateUser()) != null){
                employee.setUpdateUserName(createMap.get(employee.getUpdateUser()));
            }else{
                updateMap.put(employee.getUpdateUser(), updateUserInfo.getUsername());
                employee.setUpdateUserName(updateMap.get(employee.getUpdateUser()));
            }
        }

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());
        return pageResult;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        employeeMapper.deleteBatchIds(ids);
    }

    @Override
    public void modifyStatus(int status,Long id) {
        lambdaUpdate().eq(Employee::getId,id).set(Employee::getStatus,status).update();
    }

    @Override
    public void addEmployee(AddAndModifyEmployeeDTO addEmployeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(addEmployeeDTO,employee);
        employee.setPassword("123456");
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(ThreadLocalUserContext.getUser());
        employee.setUpdateUser(ThreadLocalUserContext.getUser());
        employee.setStatus(1);
        employeeMapper.insert(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public Result<Employee> modifyPassword(String newPassword) {
        boolean update = lambdaUpdate().eq(Employee::getId, ThreadLocalUserContext.getUser()).set(Employee::getPassword, newPassword).update();
        if(update){
            return Result.success(employeeMapper.selectById(ThreadLocalUserContext.getUser()));
        }
        return Result.error("更新失败");
    }

    @Override
    @Transactional
    public void modifyEmployee(AddAndModifyEmployeeDTO modifyEmployeeDTO) {
        Employee one = employeeMapper.selectById(modifyEmployeeDTO.getId());
        employeeMapper.deleteById(modifyEmployeeDTO.getId());

        Employee employee = new Employee();
        employee.setStatus(one.getStatus());
        employee.setPassword(one.getPassword());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateTime(one.getCreateTime());
        employee.setUpdateUser(ThreadLocalUserContext.getUser());
        employee.setCreateUser(one.getCreateUser());
        BeanUtils.copyProperties(modifyEmployeeDTO,employee);
        employeeMapper.insert(employee);
    }

}
