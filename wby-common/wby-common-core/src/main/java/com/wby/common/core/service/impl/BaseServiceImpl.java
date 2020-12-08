package com.wby.common.core.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.common.core.dto.CommonDto;
import com.wby.common.core.mapper.SuperMapper;
import com.wby.common.core.service.IBaseService;
import com.wby.common.core.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author JacksonTu
 * @Date 2019/11/7 14:35
 */
public abstract class BaseServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {


    @Override
    public PageVo pageList(CommonDto commonDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(commonDto.getPage());
        // 设置页大小
        page.setSize(commonDto.getLimit());
        IPage iPage = this.baseMapper.pageList(page, commonDto);
        return new PageVo(iPage);

    }

    @Override
    public List<Map<String, Object>> selectMapList(CommonDto commonDto) {
        return this.baseMapper.selectMapList(commonDto);
    }
}
