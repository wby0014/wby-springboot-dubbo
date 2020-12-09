package com.wby.api.base.enterprise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description
 * @Author wby
 * @Date 2019/11/7 10:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询企业部门参数对象")
public class EnterpriseDepartmentDto implements Serializable {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("企业ID")
    private Long enterpriseId;
    @ApiModelProperty("部门代码")
    private String departmentCode;
    @ApiModelProperty("部门名称")
    private String departmentName;
    @ApiModelProperty("企业名称")
    private String enterpriseName;
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;
}
