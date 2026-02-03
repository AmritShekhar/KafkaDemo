package com.amritspring.QuoraDemo.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "myapp.kafka")
public class MyKafkaProperties {
    private String bootstrapServers;
    private Consumer consumer;
    private String topic;
    private Integer concurrency;

    @Getter
    @Setter
    public static class Consumer {
        private String groupId;
    }
}
