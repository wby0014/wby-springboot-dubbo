package com.wby.api.base.generator.dto;

import com.wby.common.core.dto.CommonDto;
import lombok.Data;

/**
 * @author JacksonTu
 * @version 1.0
 * @description
 * @since 2020/11/22 11:23
 */
@Data
public class TableDto extends CommonDto {

    private String tableName;
}
