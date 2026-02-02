package com.amritspring.QuoraDemo.mappers;

import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionRequestDTO;
import com.amritspring.QuoraDemo.DTOs.QuestionDTOs.QuestionResponseDTO;
import com.amritspring.QuoraDemo.models.Question;

public class QuestionMapper {
    public static Question toEntity(QuestionRequestDTO questionRequestDTO) {
        return Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .build();
    }
    public static QuestionResponseDTO toDTO(Question question) {
        return QuestionResponseDTO.builder()
                .questionId(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
