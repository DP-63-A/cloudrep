package org.example.cloud.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.LogMessage;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogMessageProducer {

    private final MessageChannel logsChannel;
    private final ObjectMapper objectMapper;

    public void sendLog(LogMessage logMessage) {
        try {
            String json = objectMapper.writeValueAsString(logMessage);
            logsChannel.send(MessageBuilder.withPayload(json).build());
            System.out.println("Log Producer: log sent -> " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
