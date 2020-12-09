package com.wby.api.base.system.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 查询用户对象
 * @Author wby
 * @Date 2019/11/6 10:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询用户对象")
public class UserDto extends CommonDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "登录名/用户名")
    private String name;

    @ApiModelProperty(value = "创建者ID", hidden = true)
    private Long createUserId;

    @ApiModelProperty(value = "企业ID", hidden = true)
    private String enterpriseId;

    @ApiModelProperty(value = "部门ID", hidden = true)
    private String departmentId;

    @ApiModelProperty(value = "开始时间", example = "2019-10-01")
    private String startTime;

    @ApiModelProperty(value = "结束时间", example = "2019-10-01")
    private String endTime;
}
