package com.phn.mytakeout.mapper;

import com.phn.mytakeout.domain.po.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface dishMapper extends BaseMapper<Dish>{
}
