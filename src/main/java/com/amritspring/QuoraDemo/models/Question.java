package com.amritspring.QuoraDemo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "questions")
public class Question extends BaseDocument {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 100, max = 1000, message = "Content must be between 100 and 1000 characters")
    private String content;
}
