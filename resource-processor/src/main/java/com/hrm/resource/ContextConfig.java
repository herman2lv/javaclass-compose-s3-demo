package com.hrm.resource;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue-name", false);
    }

}
