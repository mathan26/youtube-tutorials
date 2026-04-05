package com.tech.with.mathan.sqs_spring_boot_demo.controller;

import com.tech.with.mathan.sqs_spring_boot_demo.dto.OrderMessage;
import com.tech.with.mathan.sqs_spring_boot_demo.service.SqsProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final SqsProducerService producerService;

    public OrderController(SqsProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderMessage orderMessage) {
        try {
            orderMessage.setOrderId(UUID.randomUUID().toString());
            orderMessage.setStatus("PENDING");

            log.info("Accepting order for queue: customerId={}, amount={}",
                    orderMessage.getCustomerId(), orderMessage.getAmount());

            producerService.sendOrder(orderMessage);

            log.info("Order queued successfully: orderId={}", orderMessage.getOrderId());

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Order accepted: " + orderMessage.getOrderId());
        } catch (Exception e) {
            log.error("Failed to accept or queue order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process order");
        }
    }
}
