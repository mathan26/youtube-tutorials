package com.tech.with.mathan.sqs_spring_boot_demo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.with.mathan.sqs_spring_boot_demo.dto.OrderMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqsConsumerService {

    private static final Logger log = LoggerFactory.getLogger(SqsConsumerService.class);
    private final ObjectMapper objectMapper;

    public SqsConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SqsListener("${app.sqs.queue-url}")
    public void receiveMessage(String messageBody) {
        try {
            // Because the producer manually serializes the object using ObjectMapper,
            // we will manually deserialize it to avoid any missing-header content type issues.
            OrderMessage orderMessage = objectMapper.readValue(messageBody, OrderMessage.class);
            log.info("Successfully consumed message for Order ID: {} with status: {}", 
                     orderMessage.getOrderId(), orderMessage.getStatus());
            
            // TODO: Add your business logic here
            
            // Example of triggering a DLQ:
            // If an exception is thrown from this listener, SQS will not delete the message.
            // After the MaxReceiveCount (configured in your AWS SQS queue's Redrive Policy) is exceeded,
            // AWS will automatically move this message to the Dead Letter Queue (DLQ).
            if ("ERROR".equalsIgnoreCase(orderMessage.getStatus())) {
                log.error("Simulating processing error. Message will be retried and eventually sent to DLQ.");
                throw new RuntimeException("Simulated processing failure for DLQ routing");
            }
            
        } catch (Exception e) {
            log.error("Failed to process message from SQS", e);
            // Re-throw so that Spring Cloud AWS doesn't acknowledge the message and it gets retried/DLQ'd
            throw new RuntimeException("Message processing failed", e);
        }
    }
}
