package com.wby.server.base.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.api.base.job.dto.JobLogDto;
import com.wby.api.base.job.entity.ScheduleJobLogEntity;
import com.wby.api.base.job.service.IScheduleJobLogService;
import com.wby.common.core.vo.PageVo;
import com.wby.server.base.job.mapper.ScheduleJobLogMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * @Description 定时任务日志服务实现类
 * @Author JacksonTu
 * @Date 2018/12/13 10:44
 */
@DubboService
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements IScheduleJobLogService {

    @Override
    public PageVo queryPage(JobLogDto jobLogDto) {
        String jobId = jobLogDto.getJobId();
        QueryWrapper<ScheduleJobLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(jobId), "job_id", jobId)
                .orderByDesc("log_id");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(jobLogDto.getPage());
        // 设置页大小
        page.setSize(jobLogDto.getLimit());
        IPage<ScheduleJobLogEntity> iPage = this.page(page, queryWrapper);
        return new PageVo(iPage);
    }

}
