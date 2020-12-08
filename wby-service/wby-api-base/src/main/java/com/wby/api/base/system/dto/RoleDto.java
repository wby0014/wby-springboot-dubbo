package com.wby.api.base.system.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 查询角色对象
 * @Author JacksonTu
 * @Date 2019/11/6 11:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询角色对象")
public class RoleDto extends CommonDto {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "创建者ID", hidden = true)
    private Long createUserId;


}
