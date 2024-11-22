package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.dish;
import com.phn.mytakeout.mapper.dishMapper;
import com.phn.mytakeout.service.dishService;
import org.springframework.stereotype.Service;

@Service
public class dishServiceImpl extends ServiceImpl<dishMapper, dish> implements dishService {
}
