package com.wby.web.base.job.controller;

import com.wby.api.base.job.dto.JobLogDto;
import com.wby.api.base.job.entity.ScheduleJobLogEntity;
import com.wby.api.base.job.service.IScheduleJobLogService;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description 定时任务日志
 * @Author wby
 * @Date 2019/1/18 15:59
 **/
@Api(value = "定时任务日志接口", tags = {"定时任务日志接口"})
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @DubboReference
    private IScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @ApiOperation(value = "定时任务日志列表", notes = "定时任务日志列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/schedule/log")
    public CommonResult<PageVo<ScheduleJobLogEntity>> list(JobLogDto jobLogDto) {
        PageVo<ScheduleJobLogEntity> page = scheduleJobLogService.queryPage(jobLogDto);

        return CommonResult.success(page);
    }

    /**
     * 定时任务日志信息
     */
    @ApiOperation(value = "定时任务日志信息", notes = "定时任务日志信息")
    @ApiImplicitParam(paramType = "path", name = "logId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{logId}")
    public CommonResult<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return CommonResult.success(log);
    }
}
