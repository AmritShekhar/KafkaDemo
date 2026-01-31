package com.amritspring.QuoraDemo.DTOs;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDTO {
    private String questionId;
    private String title;
    private String content;
    private Instant createdAt;
}
