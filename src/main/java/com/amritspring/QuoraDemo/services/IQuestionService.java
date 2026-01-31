package com.amritspring.QuoraDemo.services;

import com.amritspring.QuoraDemo.DTOs.QuestionRequestDTO;
import com.amritspring.QuoraDemo.DTOs.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    Mono<QuestionResponseDTO> getQuestionById(String id);
    Flux<QuestionResponseDTO> getAllQuestions();
    Mono<Void> deleteQuestionById(String id);
    Flux<QuestionResponseDTO> searchQuestionsUsingOffsetBasedPagination(String text, int page, int size);
}
