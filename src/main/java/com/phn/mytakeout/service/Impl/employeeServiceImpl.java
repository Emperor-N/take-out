package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
