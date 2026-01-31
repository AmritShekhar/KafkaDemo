package com.amritspring.QuoraDemo.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {
    private String title;
    private String content;
}
