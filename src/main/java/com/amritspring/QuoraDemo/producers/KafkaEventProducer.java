package com.amritspring.QuoraDemo.producers;

import com.amritspring.QuoraDemo.configurations.MyKafkaProperties;
import com.amritspring.QuoraDemo.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MyKafkaProperties myKafkaProperties;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        kafkaTemplate.send(
                myKafkaProperties.getTopic(),
                viewCountEvent.getTargetId(),
                viewCountEvent
        ).whenComplete(
                (result, error) -> {
                    if (error != null) {
                        System.out.println("Error publishing view count event: " + error.getMessage());
                    }
                }
        );
    }
}
