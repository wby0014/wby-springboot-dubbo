package com.wby.api.base.system.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @description：UserVo
 * @author：wby
 * @date 2018年5月6日 上午9:55:46
 */
@ApiModel("用户VO")
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登陆名
     */
    @ApiModelProperty(value = "登陆名")
    private String loginName;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;
    /**
     * 性别(0:男，1：女)
     */
    @ApiModelProperty(value = "性别(0:男，1：女)")
    private Integer sex;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    @ApiModelProperty(value = "用户类别（0：超级管理员，1：企业用户，2：监管用户）")
    private Integer userType;
    /**
     * 用户状态(0：正常，1：不正常)
     */
    @ApiModelProperty(value = "用户状态(0：正常，1：不正常)")
    private Integer status;
    /**
     * 过期字段（0-不过期，1-过期）
     */
    @ApiModelProperty(value = "过期字段（0-不过期，1-过期）")
    private Integer expired;
    /**
     * 所属企业
     */
    @ApiModelProperty(value = "所属企业")
    private Long enterpriseId;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private Long departmentId;
    /**
     * 用户职务
     */
    @ApiModelProperty(value = "用户职务")
    private Long jobId;
    /**
     * 是否领导（0-是，1-否）
     */
    @ApiModelProperty(value = "是否领导（0-是，1-否）")
    private Integer isLeader;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建用户ID
     */
    @ApiModelProperty(value = "创建用户ID")
    private Long createUserId;

    @ApiModelProperty(value = "所属企业")
    private String enterpriseName;

    @ApiModelProperty(value = "所属部门")
    private String departmentName;

    @ApiModelProperty(value = "所属职位")
    private String jobName;

    @ApiModelProperty(value = "用户拥有的色色")
    private String rolesList;
}