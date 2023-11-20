package com.hrm.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = "queue-name")
    public void processResourceUpload(long id) {
        log.info("Received message: {}", id);
    }

}
