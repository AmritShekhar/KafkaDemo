package com.amritspring.QuoraDemo.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerRequestDTO;
import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerResponseDTO;
import com.amritspring.QuoraDemo.enums.TargetType;
import com.amritspring.QuoraDemo.events.ViewCountEvent;
import com.amritspring.QuoraDemo.mappers.AnswerMapper;
import com.amritspring.QuoraDemo.producers.KafkaEventProducer;
import com.amritspring.QuoraDemo.repositories.IAnswerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveMongoAnswerServiceImpl implements IAnswerService {

    private final IAnswerRepository answerRepository;
    private final KafkaEventProducer kafkaEventProducer;
    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {
        return this.answerRepository
                .save(AnswerMapper.toEntity(answerRequestDTO))
                .map(AnswerMapper::toDTO)
                .doOnSuccess(
                        response -> System.out.println("Service -> Answer created successfully: " + response)
                )
                .doOnError(error -> System.out.println("Service -> Error while creating answer: " + error));
    }

    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String id) {
        return this.answerRepository
                .findById(id)
                .map(AnswerMapper::toDTO)
                .doOnSuccess(
                        response -> {
                            System.out.println("Service -> Answer fetched successfully: " + response);
                            ViewCountEvent viewCountEvent = new ViewCountEvent(id, TargetType.ANSWER, Instant.now());
                            this.kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                        }
                )
                .doOnError(error -> System.out.println("Service -> Error while fetching answer: " + error));
    }
}
