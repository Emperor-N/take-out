package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.Dish;
import com.phn.mytakeout.mapper.dishMapper;
import com.phn.mytakeout.service.dishService;
import org.springframework.stereotype.Service;

@Service
public class dishServiceImpl extends ServiceImpl<dishMapper, Dish> implements dishService {
}
