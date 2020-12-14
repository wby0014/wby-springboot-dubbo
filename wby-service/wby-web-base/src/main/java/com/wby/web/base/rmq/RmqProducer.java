package com.wby.web.base.rmq;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2020/12/14 18:06
 * @Author wuby31052
 */
@Slf4j
@Api(value = "rmq生产者", tags = {"rmq生产者"})
@RestController
@RequestMapping("/rmq")
public class RmqProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public void send() {
        rocketMQTemplate.convertAndSend("first-topic", "你好,Java旅途");
    }

}
