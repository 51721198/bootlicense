package com.kevin.snake.bootlicense.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @org.springframework.context.event.EventListener(condition = "")
    public void handleAppXXXXXXEvent(Object event){

        LOGGER.info("==>Spring<== 事件 :{} 监听成功" ,event);
    }

    @org.springframework.context.event.EventListener(condition = "")
    public void handleEurekaHeartbeatEvent(HeartbeatEvent event){

        LOGGER.info("==>Spring cloud HeartbeatEvent<== 事件 :{} 监听成功" ,event);
    }
}
