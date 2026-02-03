package com.amritspring.QuoraDemo.cosumers;

import com.amritspring.QuoraDemo.configurations.MyKafkaProperties;
import com.amritspring.QuoraDemo.events.ViewCountEvent;
import com.amritspring.QuoraDemo.repositories.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventConsumer {
    private final IQuestionRepository questionRepository;

    @KafkaListener(
            topics = "#{@myKafkaProperties.topic}",
            groupId = "#{@myKafkaProperties.consumer.groupId}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {
        this.questionRepository
                .findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    System.out.println("Incrementing view count for question: " + question.getId());
                    Integer views = question.getViewCount();
                    question.setViewCount((views == null) ? 1 : views+1);
                    return this.questionRepository.save(question);
                })
                .subscribe(updatedQuestion -> {
                    System.out.println("View count updated for question: " + updatedQuestion.getId());
                }, error -> {
                    System.out.println("Error updating view count for question: " + error.getMessage());
                });
    }
}
