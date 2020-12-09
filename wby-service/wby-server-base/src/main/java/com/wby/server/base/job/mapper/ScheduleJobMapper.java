package com.wby.server.base.job.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wby.api.base.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * @Description 定时任务
 * @Author wby
 * @Date 2018/12/13 10:44
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
