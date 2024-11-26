package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.vo.employeeVo;
import com.phn.mytakeout.mapper.employeeMapper;
import com.phn.mytakeout.properties.JwtProperties;
import com.phn.mytakeout.service.employeeService;
import com.phn.mytakeout.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class employeeServiceImpl extends ServiceImpl<employeeMapper, Employee> implements employeeService {

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    private final employeeMapper employeeMapper;

    @Override
    public employeeVo login(LoginFormDTO loginFormDTO) {

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

        employeeVo vo = new employeeVo();

        //生成token
        String token = jwtTool.createJwt(jwtProperties.getSecret(), emp.getId(), jwtProperties.getTtl());
        vo.setToken(token);
        vo.setUserName(userName);
        vo.setPassword(password);
        return vo;
    }

    @Override
    public PageResult search(EmployeeQueryDTO employeeQueryDTO) {
        Page<Employee> page = new Page<Employee>(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize());

        // 创建查询条件并执行分页查询
        Page<Employee> result = lambdaQuery()
                .like(StringUtils.isNotBlank(employeeQueryDTO.getUsername()), Employee::getUsername, employeeQueryDTO.getUsername())
//                .eq(employeeQueryDTO.getStatus() != null,Employee::getStatus,employeeQueryDTO.getStatus())
//                .lt(employeeQueryDTO.getEndTime()!=null,Employee::getCreateTime,employeeQueryDTO.getEndTime())
//                .gt(employeeQueryDTO.getStartTime()!=null,Employee::getCreateTime,employeeQueryDTO.getStartTime())
                .page(page);

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public PageResult searchByMsg(EmployeeQueryDTO employeeQueryDTO) {
        Page<Employee> page = new Page<Employee>(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize());

        // 创建查询条件并执行分页查询
        Page<Employee> result = lambdaQuery()
                .like(StringUtils.isNotBlank(employeeQueryDTO.getUsername()), Employee::getUsername, employeeQueryDTO.getUsername())
                .eq(employeeQueryDTO.getStatus() != null,Employee::getStatus,employeeQueryDTO.getStatus())
                .lt(employeeQueryDTO.getEndTime()!=null,Employee::getCreateTime,employeeQueryDTO.getEndTime())
                .gt(employeeQueryDTO.getStartTime()!=null,Employee::getCreateTime,employeeQueryDTO.getStartTime())
                .page(page);

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
    public void modifyStatus(int status,int id) {
        lambdaUpdate().eq(Employee::getId,id).set(Employee::getStatus,status).update();
    }

}
