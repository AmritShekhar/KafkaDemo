package com.amritspring.QuoraDemo.services;

import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionRequestDTO;
import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    Mono<QuestionResponseDTO> getQuestionById(String id);
    Flux<QuestionResponseDTO> getAllQuestions();
    Mono<Void> deleteQuestionById(String id);
    Flux<QuestionResponseDTO> searchQuestionsUsingOffsetBasedPagination(String searchText, int pageNumber, int pageSize);
}
