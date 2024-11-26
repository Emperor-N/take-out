package com.phn.mytakeout.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Setmeal")
public class Setmeal {
    @TableId("id")
    private int id;

    private int categoryId;//套餐分类id

    private String name;//套餐名

    private int price;//套餐价格

    private int status;//套餐售卖状态

    private String description;//套餐描述

    private String image;//图片

    private LocalDateTime createTime;//套餐创建时间

    private LocalDateTime updateTime;//套餐更新时间

    private int createUser;//创建人id

    private int updateUser;//更新人id
}
