package com.wby.web.base.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 登录表单
 * @Author wby
 * @Date 2018/6/11 17:07
 */
@ApiModel(value = "登录参数")
@Data
public class SysLoginDto {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码", hidden = true)
    private String captcha;
    @ApiModelProperty(value = "检查码", hidden = true)
    private String checkKey;

}
