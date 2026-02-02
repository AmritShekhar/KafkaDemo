package com.amritspring.QuoraDemo.DTOs.AnswerDTOs;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDTO {
    private String answerId;
    private String content;
    private Instant createdAt;
}
