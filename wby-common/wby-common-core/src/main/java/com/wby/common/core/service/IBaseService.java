package com.wby.common.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wby.common.core.dto.CommonDto;
import com.wby.common.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author JacksonTu
 * @Date 2019/11/7 14:34
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * 自定义分页
     *
     * @param commonDto
     * @return
     */
    PageVo pageList(CommonDto commonDto);

    /**
     * 自定义查询
     *
     * @param commonDto
     * @return
     */
    List<Map<String, Object>> selectMapList(CommonDto commonDto);
}
