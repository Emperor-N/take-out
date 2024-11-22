package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.employee;
import com.phn.mytakeout.mapper.employeeMapper;
import com.phn.mytakeout.service.employeeService;
import org.springframework.stereotype.Service;

@Service
public class employeeServiceImpl extends ServiceImpl<employeeMapper, employee> implements employeeService {
}
