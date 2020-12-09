package com.wby.web.base.system.controller;


import com.wby.api.base.system.dto.LogDto;
import com.wby.api.base.system.entity.SysLog;
import com.wby.api.base.system.service.ISysLogService;
import com.wby.common.core.api.CommonResult;
import com.wby.common.core.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wby
 * @description 日志管理
 * @date 2018年3月6日 上午9:42:00
 */
@Api(value = "日志管理接口", tags = {"日志管理接口"})
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @DubboReference
    private ISysLogService sysLogService;

    @ApiOperation(value = "日志列表", notes = "日志列表")
    @GetMapping("/list")
    public CommonResult<PageVo<SysLog>> dataGrid(LogDto logDto) {

        PageVo<SysLog> page = sysLogService.pageList(logDto);
        return CommonResult.success(page);
    }
}
