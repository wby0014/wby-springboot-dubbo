package com.wby.web.base.rmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2020/12/14 18:08
 * @Author wuby31052
 */
@Component
@RocketMQMessageListener(topic = "first-topic", selectorExpression = "*", consumerGroup = "wby-consumer-group")
@Slf4j
public class RmqConsumer implements RocketMQListener<MessageExt> {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(MessageExt message) {
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("接收到消息：{}", msg);
    }
}
