package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.EmployeeQueryDTO;
import com.phn.mytakeout.domain.dto.LoginFormDTO;
import com.phn.mytakeout.domain.po.employee;
import com.phn.mytakeout.domain.vo.employeeVo;
import com.phn.mytakeout.mapper.employeeMapper;
import com.phn.mytakeout.properties.JwtProperties;
import com.phn.mytakeout.service.employeeService;
import com.phn.mytakeout.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class employeeServiceImpl extends ServiceImpl<employeeMapper, employee> implements employeeService {

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    private final employeeMapper employeeMapper;

    @Override
    public employeeVo login(LoginFormDTO loginFormDTO) {

        String userName = loginFormDTO.getUsername();
        String password = loginFormDTO.getPassword();

        //查询username的记录
        employee emp = lambdaQuery().eq(employee::getUserName, userName).one();

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
        Page<employee> page = new Page<>(employeeQueryDTO.getPage(), employeeQueryDTO.getPageSize());

// 创建查询条件
        LambdaQueryWrapper<employee> queryWrapper = Wrappers.lambdaQuery();

// 检查 username 是否为空
        if (StringUtils.isNotBlank(employeeQueryDTO.getUsername())) {
            // 如果 username 不为空，添加模糊查询条件
            queryWrapper.like(employee::getUserName, employeeQueryDTO.getUsername());
        }

// 执行分页查询
        Page<employee> employeePage = employeeMapper.selectPage(page, queryWrapper);

// 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(20L); // 这里的 total 会根据查询条件返回
        pageResult.setRecord(employeePage.getRecords());

        return pageResult;
    }
}
