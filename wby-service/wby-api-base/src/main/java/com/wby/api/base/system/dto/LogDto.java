package com.wby.api.base.system.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 查询日志对象
 * @Author JacksonTu
 * @Date 2019/11/6 15:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询日志对象")
public class LogDto extends CommonDto {

    @ApiModelProperty(value = "登录名", example = "admin")
    private String loginName;

    @ApiModelProperty(value = "日志类型（0:操作日志，1：登录日志）", example = "0")
    private Integer logType;

    @ApiModelProperty(value = "开始时间", example = "2019-10-01")
    private String startTime;

    @ApiModelProperty(value = "结束时间", example = "2019-10-01")
    private String endTime;

}
