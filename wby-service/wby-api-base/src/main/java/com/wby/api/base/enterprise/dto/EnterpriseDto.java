package com.wby.api.base.enterprise.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author JacksonTu
 * @Date 2019/11/7 10:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询企业参数对象")
public class EnterpriseDto extends CommonDto {
    @ApiModelProperty("企业ID")
    private Long enterpriseId;
    @ApiModelProperty("企业名称")
    private String enterpriseName;
    @ApiModelProperty("企业代码")
    private String enterpriseCode;
    @ApiModelProperty("企业类型")
    private String enterpriseType;
    @ApiModelProperty("区域码")
    private String areaCode;
    @ApiModelProperty("行业码")
    private String industryCode;
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;
}
