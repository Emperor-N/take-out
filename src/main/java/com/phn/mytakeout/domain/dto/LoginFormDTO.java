package com.phn.mytakeout.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录表单实体")
public class LoginFormDTO {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "用户名", required = true)
    private String password;
    @ApiModelProperty(value = "是否记住我", required = false)
    private Boolean rememberMe = false;
}
