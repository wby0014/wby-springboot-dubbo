package com.wby.web.base.rmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2020/12/14 18:08
 * @Author wuby31052
 */
@Component
@RocketMQMessageListener(topic = "first-topic", consumerGroup = "wby-consumer-group")
@Slf4j
public class RmqConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("我收到消息了！消息内容为：" + message);
    }

}
