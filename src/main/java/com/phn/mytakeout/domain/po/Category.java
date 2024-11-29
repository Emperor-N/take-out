package com.phn.mytakeout.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("Category")
public class Category {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;//用户名

    private int type;//所属类型

    private int status;//分类状态

    private String image;//图片

    private String description;

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private Long createUser;//创建人id

    private Long updateUser;//更新人id
}
