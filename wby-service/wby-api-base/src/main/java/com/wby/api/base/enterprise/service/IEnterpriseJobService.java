package com.wby.api.base.enterprise.service;

import com.wby.api.base.enterprise.entity.EnterpriseJob;
import com.wby.common.core.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author wby
 * @date 2018-12-11 11:36:02
 */
public interface IEnterpriseJobService extends IBaseService<EnterpriseJob> {
    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<EnterpriseJob> selectEnterpriseJobList(Map<String, Object> params);

}

