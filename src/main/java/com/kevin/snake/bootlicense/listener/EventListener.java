package com.kevin.snake.bootlicense.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    @org.springframework.context.event.EventListener(condition = "")
    public void handleAppXXXXXXEvent(Object event){

        log.info("==>Spring<== 事件 :{} 监听成功" ,event);
    }

    @org.springframework.context.event.EventListener(condition = "")
    public void handleEurekaHeartbeatEvent(HeartbeatEvent event){

        log.info("==>Spring cloud HeartbeatEvent<== 事件 :{} 监听成功" ,event);
    }
}
