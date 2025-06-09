package org.example.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class LogQueueConfig {

    @Bean
    public MessageChannel logsChannel() {
        return new DirectChannel();
    }
}
