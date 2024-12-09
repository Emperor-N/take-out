package com.phn.mytakeout.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Dish")
public class Dish {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(exist = false)
    private String categoryName;//冗余，数据库并不存在该字段

    private String name;//菜品名

    private Long categoryId;//分类id

    private BigDecimal price;//价格

    private String image;//图片

    private String description;//菜品描述

    private Integer status;//售卖状态

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private Long createUser;//创建人id

    private Long updateUser;//更新人id

    @TableField(exist = false)//非数据库字段需声明false
    private String createUserName;//用于前端展示名字而不是id

    @TableField(exist = false)//非数据库字段需声明false
    private String updateUserName;//用于前端展示名字而不是id
}
