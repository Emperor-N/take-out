package com.phn.mytakeout.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("Employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;//用户名

    private String password;//密码

    private Integer gender;//性别

    private Integer status;//账号状态

    private String image;//图片

    private String idNumber;//身份证号

    private String number;//电话号码

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private Long createUser;//创建人id

    @TableField(exist = false)//非数据库字段需声明false
    private String createUserName;//用于前端展示名字而不是id

    private Long updateUser;//更新人id

    @TableField(exist = false)//非数据库字段需声明false
    private String updateUserName;//用于前端展示名字而不是id
}
