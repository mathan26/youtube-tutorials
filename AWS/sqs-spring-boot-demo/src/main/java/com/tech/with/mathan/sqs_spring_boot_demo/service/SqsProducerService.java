package com.tech.with.mathan.sqs_spring_boot_demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.with.mathan.sqs_spring_boot_demo.dto.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.concurrent.CompletableFuture;

@Service
public class SqsProducerService {
    private static final Logger log = LoggerFactory.getLogger(SqsProducerService.class);

    private final SqsAsyncClient sqsAsyncClient;
    private final ObjectMapper objectMapper;

    @Value("${app.sqs.queue-url}")
    private String queueUrl;

    public SqsProducerService(SqsAsyncClient sqsAsyncClient, ObjectMapper objectMapper) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(OrderMessage order) {
        try {
            String messageBody = objectMapper.writeValueAsString(order);
            SendMessageRequest send_msg_request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .build();

            CompletableFuture<SendMessageResponse> future = sqsAsyncClient.sendMessage(send_msg_request);
            
            future.whenComplete((response, exception) -> {
                if (exception != null) {
                    log.error("Failed to send orderId: {}", order.getOrderId(), exception);
                } else {
                    log.info("Successfully sent orderId: {} to SQS. Message ID: {}", order.getOrderId(), response.messageId());
                }
            });
        } catch (Exception e) {
            log.error("Error serializing or sending orderId: {}", order.getOrderId(), e);
        }
    }
}
