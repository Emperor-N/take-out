package com.phn.mytakeout.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("Setmeal_dish")
public class SetmealDish {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long setmealId;//套餐id

    private Long dishId;//菜品id

    private String name;//菜品名

    private BigDecimal price;//价格

    private Integer copies;//菜品份数
}
