package com.wby.server.base.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.api.base.system.entity.SysDic;
import com.wby.api.base.system.service.ISysDicService;
import com.wby.api.base.system.vo.DicVo;
import com.wby.server.base.system.mapper.SysDicMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author JacksonTu
 * @since 2018-04-26
 */
@Slf4j
@DubboService(interfaceName = "ISysDicService")
@Transactional(rollbackFor = Exception.class)
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements ISysDicService {

    @Override
    public List<DicVo> selectTreeGrid(Map<String, Object> par) {

        return this.baseMapper.selectTreeGrid(par);
    }

    @Override
    public List<SysDic> selectDicList(Map<String, Object> params) {
        return this.baseMapper.selectDicList(params);
    }


    @Override
    public List<Map<String, Object>> selectTreeByParentId(Map<String, Object> par) {

        return this.baseMapper.selectTreeByParentId(par);
    }
}
