package com.wby.api.base.enterprise.service;


import com.wby.api.base.enterprise.entity.Enterprise;
import com.wby.common.core.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 *
 * @author JacksonTu
 * @date 2018-12-11 13:49:00
 */
public interface IEnterpriseService extends IBaseService<Enterprise> {

    /**
     * 多表信息查询
     *
     * @param par
     * @return
     */
    List<Map<String, Object>> selectEnterpriseList(Map<String, Object> par);


}

