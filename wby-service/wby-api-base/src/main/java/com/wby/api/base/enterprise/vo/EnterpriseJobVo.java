package com.wby.api.base.enterprise.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业职务配置表
 *
 * @author wby
 * @date 2018-12-11 11:36:02
 */
@ApiModel(value = "企业职务配置表")
@Data
@EqualsAndHashCode
public class EnterpriseJobVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 企业部门表ID
     */
    @ApiModelProperty(value = "企业部门表ID")
    private Long departmentId;
    /**
     * 职务代码
     */
    @ApiModelProperty(value = "职务代码")
    private String jobCode;
    /**
     * 职务名称
     */
    @ApiModelProperty(value = "职务名称")
    private String jobName;
    /**
     * 记录创建时间
     */
    @ApiModelProperty(value = "记录创建时间")
    private Date createTime;
    /**
     * 记录最后更新时间
     */
    @ApiModelProperty(value = "记录最后更新时间")
    private Date updateTime;
    /**
     * 记录创建用户
     */
    @ApiModelProperty(value = "记录创建用户")
    private String createUser;
    /**
     * 记录最后更新用户
     */
    @ApiModelProperty(value = "记录最后更新用户")
    private String updateUser;
    /**
     * 预留1
     */
    @ApiModelProperty(value = "预留1")
    private String parameter1;
    /**
     * 预留2
     */
    @ApiModelProperty(value = "预留2")
    private String parameter2;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private String enterpriseId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
}
