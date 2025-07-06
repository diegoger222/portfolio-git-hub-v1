package com.w_backend.demo.common.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import java.util.logging.Logger;

@Profile({ "!test" })
@Configuration
public class KafkaTopic {

    private static final Logger logger = Logger.getLogger(KafkaTopic.class.getName());

    @Bean
    public NewTopic topic1() {
        logger.info("Creating topic-1");
        return TopicBuilder.name("topic-1").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic-2").partitions(3).build();
    }
}