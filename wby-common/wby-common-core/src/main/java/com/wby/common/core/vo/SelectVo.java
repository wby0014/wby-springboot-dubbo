package com.wby.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wby.common.core.utils.JacksonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description vue select选择器对象
 * @Author wby
 * @Date 2018/6/20 10:38
 */
@ApiModel("vue select选择器对象")
public class SelectVo implements Serializable {
    /**
     * value
     */
    @ApiModelProperty("key")
    private String value;

    /**
     * label
     */
    @ApiModelProperty("value")
    private String label;

    @ApiModelProperty("对象List")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SelectVo> options;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SelectVo> getOptions() {
        return options;
    }

    public void setOptions(List<SelectVo> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
