package com.w_backend.demo.common.services.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.w_backend.demo.common.utils.AnsiColor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String topicName) {
        log.info(AnsiColor.BLUE + "Sending : {}" + AnsiColor.RESET, message);
        log.info(AnsiColor.BLUE + "--------------------------------" + AnsiColor.RESET);

        kafkaTemplate.send(topicName, message);
    }
}
