package org.example.cloud.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cloud.dto.LogMessage;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class LogMessageConsumer {

    private final ObjectMapper objectMapper;

    public LogMessageConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ServiceActivator(inputChannel = "logsChannel")
    public void handleLog(String payload) {
        try {
            LogMessage log = objectMapper.readValue(payload, LogMessage.class);
            System.out.printf("Log [%s] at %s -> %s%n",
                    log.getLevel(),
                    log.getTimestamp(),
                    log.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
