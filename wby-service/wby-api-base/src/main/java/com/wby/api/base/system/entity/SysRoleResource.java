package com.wby.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wby.common.core.utils.JacksonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色资源表
 *
 * @author wby
 * @date 2018-12-11 11:35:15
 */
@ApiModel(value = "角色资源表")
@TableName("t_sys_role_resource")
public class SysRoleResource implements Serializable {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    @TableField("resource_id")
    private Long resourceId;

    @TableField(exist = false)
    private SysRole role;

    @TableField(exist = false)
    private SysResource resource;

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：资源id
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置：资源id
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysResource getResource() {
        return resource;
    }

    public void setResource(SysResource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
