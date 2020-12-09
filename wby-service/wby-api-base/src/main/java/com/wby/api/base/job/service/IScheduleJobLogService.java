package com.wby.api.base.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wby.api.base.job.dto.JobLogDto;
import com.wby.api.base.job.entity.ScheduleJobLogEntity;
import com.wby.common.core.vo.PageVo;


/**
 * @Description 定时任务日志
 * @Author wby
 * @Date 2019/1/18 15:59
 **/
public interface IScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageVo queryPage(JobLogDto jobLogDto);

}
