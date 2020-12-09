package com.wby.common.core.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Description 分页参数
 * @Author wby
 * @Date 2019/11/6 9:44
 */
@ApiModel("分页参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class PageVo<T> implements Serializable {

    /**
     * 总行数
     */
    @ApiModelProperty("总行数")
    private int totalCount;

    //
    /**
     * 列表数据
     */
    @ApiModelProperty("数据列表")
    private List<T> list = Collections.emptyList();

    public PageVo() {

    }

    /**
     * 分页
     */
    public PageVo(IPage page) {
        this.list = page.getRecords();
        this.totalCount = new Long(page.getTotal()).intValue();
    }
}
