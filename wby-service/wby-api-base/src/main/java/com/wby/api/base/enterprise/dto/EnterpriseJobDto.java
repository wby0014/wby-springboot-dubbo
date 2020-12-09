package com.wby.api.base.enterprise.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author wby
 * @Date 2019/11/7 10:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询消息类型参数对象")
public class EnterpriseJobDto extends CommonDto {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("企业ID")
    private Long enterpriseId;
    @ApiModelProperty("职位代码")
    private String jobCode;
    @ApiModelProperty("职位名称")
    private String jobName;
    @ApiModelProperty("部门ID")
    private Long departmentId;
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;
}
