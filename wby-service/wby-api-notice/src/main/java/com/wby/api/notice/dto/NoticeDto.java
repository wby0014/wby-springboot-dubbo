package com.wby.api.notice.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @version 1.0
 * @description 查询企业参数对象
 * @since 2020/11/21 15:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询企业参数对象")
public class NoticeDto extends CommonDto {

    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("消息类型")
    private String msgCategory;


}
