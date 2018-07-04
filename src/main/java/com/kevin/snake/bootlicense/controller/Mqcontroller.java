package com.kevin.snake.bootlicense.controller;

import com.alibaba.fastjson.JSON;
import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import com.kevin.snake.bootlicense.pojo.ProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liudun on 2017/5/9.
 */

@RestController
@RequestMapping("mq")
@Slf4j
public class Mqcontroller {
//
//    @Autowired
//    private KafkaProducer kafkaProducer;

    @Value("${kafka.topic}")
    private String topic;

    @RequestMapping("produce")
    public String produce() {
        ProcessResult<String> result = new ProcessResult<>();
        for (int i = 0; i < 20; i++) {
//            kafkaProducer.send(topic, "测试发送数据:" + i);
            log.info("现在发送第 {} 条信息." + i);
            log.info("现在发送第 {} 条信息.", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("数据发送完毕.");
        result.setResult(ProcessResultEnum.SUCCESS,"队列数据已经发送完毕");
        return JSON.toJSONString(result);
    }

//    @RequestMapping("consume")
//    public String consume(){
//        kafkaConsumer.consume();
//        return null;
//    }

}
