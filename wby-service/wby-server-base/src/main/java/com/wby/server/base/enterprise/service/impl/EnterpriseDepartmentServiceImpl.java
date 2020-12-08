package com.wby.server.base.enterprise.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.api.base.enterprise.dto.EnterpriseDepartmentDto;
import com.wby.api.base.enterprise.entity.EnterpriseDepartment;
import com.wby.api.base.enterprise.service.IEnterpriseDepartmentService;
import com.wby.api.base.enterprise.vo.EnterpriseDepartmentVo;
import com.wby.server.base.enterprise.mapper.EnterpriseDepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业部门表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Slf4j
@DubboService(interfaceName = "IEnterpriseDepartmentService")
@Transactional(rollbackFor = Exception.class)
public class EnterpriseDepartmentServiceImpl extends ServiceImpl<EnterpriseDepartmentMapper, EnterpriseDepartment> implements IEnterpriseDepartmentService {


    @Override
    public List<EnterpriseDepartmentVo> selectTreeGrid(EnterpriseDepartmentDto enterpriseDepartmentDto) {
        return this.baseMapper.selectTreeGrid(enterpriseDepartmentDto);
    }

    @Override
    public List<EnterpriseDepartment> selectEnterpriseDepartmentList(Map<String, Object> params) {
        return this.baseMapper.selectEnterpriseDepartmentList(params);
    }
}
