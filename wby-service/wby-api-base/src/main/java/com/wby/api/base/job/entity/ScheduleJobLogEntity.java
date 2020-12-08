package com.wby.api.base.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 定时任务日志
 * @Author JacksonTu
 * @Date 2019/1/18 15:59
 **/
@ApiModel("定时任务日志")
@TableName("t_schedule_job_log")
public class ScheduleJobLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @ApiModelProperty("日志ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 任务id
     */
    @ApiModelProperty("任务ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long jobId;

    /**
     * spring bean名称
     */
    @ApiModelProperty("spring bean名称")
    private String beanName;

    /**
     * 方法名
     */
    @ApiModelProperty("方法名")
    private String methodName;

    /**
     * 参数
     */
    @ApiModelProperty("参数")
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    @ApiModelProperty("任务状态(0：成功,1：失败)")
    private Integer status;

    /**
     * 失败信息
     */
    @ApiModelProperty("失败信息")
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    @ApiModelProperty("耗时(单位：毫秒)")
    private Integer times;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
