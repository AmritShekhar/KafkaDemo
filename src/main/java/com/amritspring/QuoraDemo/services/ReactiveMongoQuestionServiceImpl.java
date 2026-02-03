package com.amritspring.QuoraDemo.services;

import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionRequestDTO;
import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionResponseDTO;
import com.amritspring.QuoraDemo.enums.TargetType;
import com.amritspring.QuoraDemo.events.ViewCountEvent;
import com.amritspring.QuoraDemo.mappers.QuestionMapper;
import com.amritspring.QuoraDemo.producers.KafkaEventProducer;
import com.amritspring.QuoraDemo.repositories.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReactiveMongoQuestionServiceImpl implements IQuestionService {

    private final IQuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        return this.questionRepository
                .save(QuestionMapper.toEntity(questionRequestDTO))
                .map(QuestionMapper::toDTO)
                .doOnSuccess(
                        response -> System.out.println("Service -> Question created successfully: " + response)
                )
                .doOnError(error -> System.out.println("Service -> Error while creating question: " + error));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return this.questionRepository
                .findById(id)
                .map(QuestionMapper::toDTO)
                .doOnSuccess(
                        response -> {
                            System.out.println("Service -> Question fetched successfully: " + response);
                            ViewCountEvent viewCountEvent = new ViewCountEvent(id, TargetType.QUESTION, Instant.now());
                            this.kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                        }
                ).doOnError(error -> System.out.println("Service -> Error while fetching question: " + error));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return this.questionRepository
                .findAll()
                .map(QuestionMapper::toDTO)
                .doOnError(error -> System.out.println("Service -> Error while fetching questions: " + error))
                .doOnComplete(
                        () -> System.out.println("Service -> Questions fetched successfully!")
                );
    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return this.questionRepository
                .deleteById(id)
                .doOnSuccess(
                        response -> System.out.println("Service -> Question deleted successfully: " + response)
                )
                .doOnError(error -> System.out.println("Service -> Error while deleting question: " + error));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestionsUsingOffsetBasedPagination(
            String searchText,
            int pageNumber,
            int pageSize
    ) {
        return this.questionRepository
                .findByTitleOrContentContainingIgnoreCase(searchText, PageRequest.of(pageNumber, pageSize))
                .map(QuestionMapper::toDTO)
                .doOnError(error -> System.out.println("Service -> Error while fetching questions: " + error))
                .doOnComplete(
                        () -> System.out.println("Service -> Questions fetched successfully!")
                );
    }
}
