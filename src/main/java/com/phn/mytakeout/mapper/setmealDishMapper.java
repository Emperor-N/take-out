package com.phn.mytakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phn.mytakeout.domain.po.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface setmealDishMapper extends BaseMapper<SetmealDish> {
    @Delete("delete from Setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);
}
