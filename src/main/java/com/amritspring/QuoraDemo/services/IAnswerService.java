package com.amritspring.QuoraDemo.services;

import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerRequestDTO;
import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);
    Mono<AnswerResponseDTO> getAnswerById(String id);
}
