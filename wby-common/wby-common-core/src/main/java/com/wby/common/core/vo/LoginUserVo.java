package com.wby.common.core.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wby.common.core.utils.JacksonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @Description 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @Author JacksonTu
 * @Date 2018/12/12 19:03
 */
@ApiModel("登录信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUserVo implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登陆名
     */
    @ApiModelProperty("登陆名")
    private String loginName;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;
//    /**
//     * 密码
//     */
//    @ApiModelProperty(value = "密码", hidden = true)
//    private String password;
//    /**
//     * 盐值
//     */
//    @ApiModelProperty(value = "盐值", hidden = true)
//    private String salt;
    /**
     * 性别(0:男，1：女)
     */
    @ApiModelProperty("性别(0:男，1：女)")
    private Integer sex;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    @ApiModelProperty("用户类别（0：超级管理员，1：企业用户，2：监管用户）")
    private Integer userType;
    /**
     * 用户状态(0：正常，1：不正常)
     */
    @ApiModelProperty("用户状态(0-正常，1-不正常)")
    private Integer status;
    /**
     * 过期字段（0-不过期，1-过期）
     */
    @ApiModelProperty("过期字段（0-不过期，1-过期）")
    private Integer expired;
    /**
     * 所属企业
     */
    @ApiModelProperty("所属企业ID")
    private Long enterpriseId;
    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门ID")
    private Long departmentId;
    /**
     * 用户职务
     */
    @ApiModelProperty("用户职务ID")
    private Long jobId;
    /**
     * 是否领导（0-是，1-否）
     */
    @ApiModelProperty("是否领导（0-是，1-否）")
    private Integer isLeader;
    /**
     * 记录创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 用户管理的企业ID集合
     */
    @ApiModelProperty("用户管理的企业ID集合")
    private List<Long> enterpriseIdList;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

}