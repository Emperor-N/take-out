package com.phn.mytakeout.domain.vo;

import com.phn.mytakeout.domain.po.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetmealVO {
    private Long id;

    private Long categoryId;//分类id

    private String name;//套餐名

    private BigDecimal price;//价格

    private Integer status;//套餐状态

    private String description;//描述

    private String image;//图片

    private LocalDate UpdateTime;//更细时间

    private String categoryName;//分类名称

    private List<SetmealDish> setmealDishes = new ArrayList<>();//套餐和菜品的关联关系
}
