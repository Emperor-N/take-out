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
@TableName("Setmeal")
public class Setmeal {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(exist = false)
    private String categoryName;//冗余，数据库并不存在该字段

    private Long categoryId;//套餐分类id

    private String name;//套餐名

    private BigDecimal price;//套餐价格

    private Integer status;//套餐售卖状态

    private String description;//套餐描述

    private String image;//图片

    private LocalDateTime createTime;//套餐创建时间

    private LocalDateTime updateTime;//套餐更新时间

    private Long createUser;//创建人id

    private Long updateUser;//更新人id

    @TableField(exist = false)
    private String createUserName;

    @TableField(exist = false)
    private String updateUserName;
}
