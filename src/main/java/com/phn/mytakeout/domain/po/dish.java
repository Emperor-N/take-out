package com.phn.mytakeout.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class dish {
    private int id;

    private String name;//菜品名

    private int categoryId;//分类id

    private int price;//价格

    private String image;//图片

    private String description;//菜品描述

    private int status;//售卖状态

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private int createUser;//创建人id

    private int updateUser;//更新人id
}
