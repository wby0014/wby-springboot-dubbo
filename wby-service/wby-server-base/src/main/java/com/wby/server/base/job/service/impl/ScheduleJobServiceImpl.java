package com.wby.server.base.job.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wby.api.base.job.dto.JobDto;
import com.wby.api.base.job.entity.ScheduleJobEntity;
import com.wby.api.base.job.entity.ScheduleJobLogEntity;
import com.wby.api.base.job.service.IScheduleJobService;
import com.wby.common.core.constant.CommonConstant;
import com.wby.common.core.vo.PageVo;
import com.wby.server.base.job.mapper.ScheduleJobMapper;
import com.wby.server.base.job.util.ScheduleUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.quartz.Scheduler;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 定时任务服务实现类
 * @Author JacksonTu
 * @Date 2018/12/13 10:44
 */
@DubboService
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobEntity> implements IScheduleJobService {

    @Resource
    private Scheduler scheduler;

    @Override
    public PageVo queryPage(JobDto jobDto) {
        String beanName = jobDto.getBeanName();
        QueryWrapper<ScheduleJobEntity> queryWrapper = new QueryWrapper<ScheduleJobEntity>();
        queryWrapper.like(StringUtils.isNotBlank(beanName), "job_id", beanName)
                .orderByDesc("job_id");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(jobDto.getPage());
        // 设置页大小
        page.setSize(jobDto.getLimit());
        IPage<ScheduleJobLogEntity> iPage = this.page(page, queryWrapper);
        return new PageVo(iPage);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(CommonConstant.NORMAL);
        this.save(scheduleJob);

        ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);

        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", Arrays.asList(jobIds));
        map.put("status", status);
        return this.baseMapper.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, CommonConstant.PAUSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, CommonConstant.NORMAL);
    }

}
