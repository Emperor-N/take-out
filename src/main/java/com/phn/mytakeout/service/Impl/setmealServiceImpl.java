package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.Setmeal;
import com.phn.mytakeout.mapper.setmealMapper;
import com.phn.mytakeout.service.setmealService;
import org.springframework.stereotype.Service;

@Service
public class setmealServiceImpl extends ServiceImpl<setmealMapper, Setmeal> implements setmealService {
}
