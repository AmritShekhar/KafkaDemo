package com.amritspring.QuoraDemo.mappers;

import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerRequestDTO;
import com.amritspring.QuoraDemo.DTOs.AnswerDTOs.AnswerResponseDTO;
import com.amritspring.QuoraDemo.models.Answer;

public class AnswerMapper {
    public static Answer toEntity(AnswerRequestDTO answerRequestDTO) {
        return Answer.builder()
        .content(answerRequestDTO.getContent())
        .build();
    }

    public static AnswerResponseDTO toDTO(Answer answer) {
        return AnswerResponseDTO.builder()
        .answerId(answer.getId())
        .content(answer.getContent())
        .createdAt(answer.getCreatedAt())
        .build();
    }
    
}
