package com.wby.api.base.system.dto;

import com.wby.common.core.dto.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author wby
 * @Date 2019/11/7 11:51
 */
@ApiModel("查询文件参数对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class FileDto extends CommonDto {

    private String id;
    /**
     * 附件类型(哪个表的附件)
     */
    @ApiModelProperty(value = "附件类型(哪个表的附件)")
    private String tableId;
    /**
     * 附件ID(哪个表的记录Id)
     */
    @ApiModelProperty(value = "附件ID(哪个表的记录Id)")
    private String recordId;
    /**
     * 表的记录Id下的附件分组的组名
     */
    @ApiModelProperty(value = "表的记录Id下的附件分组的组名")
    private String attachmentGroup;
    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String attachmentName;
    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径")
    private String attachmentPath;
    /**
     * 附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)
     */
    @ApiModelProperty(value = "附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)")
    private Integer attachmentType;

    /**
     * 存储类型（0：本地存储，1:fastdfs）
     */
    @ApiModelProperty(value = "存储类型（0：本地存储，1:fastdfs）")
    private Integer saveType;
}
