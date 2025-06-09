package org.example.cloud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.TransactionMessage;
import org.example.cloud.dto.LogMessage;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionMessageProducer {

    private final MessageChannel outputChannel;
    private final ObjectMapper objectMapper;
    private final LogMessageProducer logMessageProducer;

    public void sendMessage(TransactionMessage transactionMessage) {
        try {
            String json = objectMapper.writeValueAsString(transactionMessage);
            outputChannel.send(MessageBuilder.withPayload(json).build());
            System.out.println("Producer: message sent -> " + json);

            logMessageProducer.sendLog(new LogMessage(
                    "INFO",
                    "Transaction message sent: " + json,
                    LocalDateTime.now()
            ));
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            logMessageProducer.sendLog(new LogMessage(
                    "ERROR",
                    "Failed to serialize transaction message: " + e.getMessage(),
                    LocalDateTime.now()
            ));
        }
    }
}
