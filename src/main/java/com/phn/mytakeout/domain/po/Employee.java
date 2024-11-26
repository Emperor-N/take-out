package com.phn.mytakeout.domain.po;

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
    @TableId("id")
    private Long id;

    private String username;//用户名

    private String password;//密码

    private String gender;//性别

    private int status;//账号状态

    private String image;//图片

    private String idNumber;//图片

    private String number;//电话号码

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private int createUser;//创建人id

    private int updateUser;//更新人id
}
