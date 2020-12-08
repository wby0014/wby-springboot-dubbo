package com.wby.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@ApiModel(value = "系统日志表")
@TableName("t_sys_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 登陆名
     */
    @ApiModelProperty(value = "登陆名")
    @TableField("login_name")
    private String loginName;
    /**
     * 日志类型（0:操作日志，1：登录日志）
     */
    @ApiModelProperty(value = "日志类型（0:操作日志，1：登录日志）")
    @TableField("log_type")
    private Integer logType;

    /**
     * 日志内容
     */
    @ApiModelProperty(value = "日志内容")
    @TableField("log_content")
    private String logContent;

    /**
     * 操作类型（添加;2:修改;3:删除）
     */
    @ApiModelProperty(value = "操作类型（添加;2:修改;3:删除）")
    @TableField("operate_type")
    private Integer operateType;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    @TableField("class_name")
    private String className;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String method;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 执行时长
     */
    @ApiModelProperty(value = "执行时长")
    private Long time;
    /**
     * 客户端ip
     */
    @ApiModelProperty(value = "客户端ip")
    @TableField("client_ip")
    private String clientIp;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

}
