package com.phn.mytakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phn.mytakeout.domain.po.orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface orderMapper extends BaseMapper<orders> {
}
