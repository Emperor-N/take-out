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
public class employee {
    private int id;

    private String positionName;//管理员或普通员工

    private String userName;//用户名

    private String password;//密码

    private String gender;//性别

    private String number;//电话号码

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间

    private int createUser;//创建人id

    private int updateUser;//更新人id
}
