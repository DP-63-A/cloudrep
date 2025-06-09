package org.example.cloud.service;

import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.LogMessage;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionMessageConsumer {

    private final LogMessageProducer logMessageProducer;

    @ServiceActivator(inputChannel = "outputChannel")
    public void handleMessage(String message) {
        System.out.println("Consumer: message received -> " + message);

        logMessageProducer.sendLog(new LogMessage(
                "INFO",
                "Transaction message consumed: " + message,
                LocalDateTime.now()
        ));
    }
}
