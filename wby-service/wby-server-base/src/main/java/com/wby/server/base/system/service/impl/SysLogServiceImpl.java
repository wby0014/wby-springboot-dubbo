package com.wby.server.base.system.service.impl;


import com.wby.api.base.system.entity.SysLog;
import com.wby.api.base.system.service.ISysLogService;
import com.wby.common.core.service.impl.BaseServiceImpl;
import com.wby.common.core.utils.SpringUtil;
import com.wby.server.base.system.mapper.SysLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统日志表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@DubboService(interfaceName = "ISysLogService")
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public void addLog(String username, String logContent, Integer logType, Integer operateType) {
        SysLog sysLog = new SysLog();
        sysLog.setLoginName(username);
        //注解上的描述,操作日志内容
        sysLog.setLogContent(logContent);
        sysLog.setLogType(logType);
        sysLog.setOperateType(operateType);

        //请求的方法名
        //请求的参数
        try {
            //获取request
            HttpServletRequest request = SpringUtil.getHttpServletRequest();
            //设置IP地址
            sysLog.setClientIp(request.getRemoteAddr());
        } catch (Exception e) {
            sysLog.setClientIp("127.0.0.1");
        }
        sysLog.setCreateTime(new Date());
        //保存系统日志
        this.baseMapper.insert(sysLog);
    }

}
