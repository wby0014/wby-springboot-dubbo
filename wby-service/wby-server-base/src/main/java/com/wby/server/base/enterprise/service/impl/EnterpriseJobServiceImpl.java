package com.wby.server.base.enterprise.service.impl;


import com.wby.api.base.enterprise.entity.EnterpriseJob;
import com.wby.api.base.enterprise.service.IEnterpriseJobService;
import com.wby.common.core.service.impl.BaseServiceImpl;
import com.wby.server.base.enterprise.mapper.EnterpriseJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author wby
 * @date 2018-12-11 11:36:02
 */
@Slf4j
@DubboService(interfaceName = "IEnterpriseJobService")
@Transactional(rollbackFor = Exception.class)
public class EnterpriseJobServiceImpl extends BaseServiceImpl<EnterpriseJobMapper, EnterpriseJob> implements IEnterpriseJobService {

    @Override
    public List<EnterpriseJob> selectEnterpriseJobList(Map<String, Object> par) {

        return this.baseMapper.selectEnterpriseJobList(par);
    }
}
