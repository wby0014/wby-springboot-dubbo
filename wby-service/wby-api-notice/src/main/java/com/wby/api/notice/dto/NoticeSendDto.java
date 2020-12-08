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
 * @since 2020/11/21 15:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询企业参数对象")
public class NoticeSendDto extends CommonDto {

    @ApiModelProperty("通知ID")
    private Long noticeId;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("阅读状态")
    private String readFlag;
    @ApiModelProperty("消息类型")
    private String msgCategory;
    @ApiModelProperty("业务类型")
    private String busType;
}
