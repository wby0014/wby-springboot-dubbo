package com.wby.server.base.enterprise.mapper;

import com.wby.api.base.enterprise.entity.EnterpriseJob;
import com.wby.common.core.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author wby
 * @date 2018-12-11 11:36:02
 */
public interface EnterpriseJobMapper extends SuperMapper<EnterpriseJob> {

    /**
     * 自定义查询
     *
     * @param params
     * @return
     */
    List<EnterpriseJob> selectEnterpriseJobList(@Param("ew") Map<String, Object> params);
}
