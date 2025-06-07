package com.w_backend.demo.common.services.kafka.service.input;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.w_backend.demo.common.utils.AnsiColor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListenerExample {

    @KafkaListener(topics = "topic-1", groupId = "group1")
    void listener(String data) {
        log.info(AnsiColor.GREEN + "Received message [{}] in group1" + AnsiColor.RESET, data);
    }
}
