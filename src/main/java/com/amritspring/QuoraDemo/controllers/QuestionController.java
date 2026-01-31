package com.amritspring.QuoraDemo.controllers;

import com.amritspring.QuoraDemo.DTOs.QuestionRequestDTO;
import com.amritspring.QuoraDemo.DTOs.QuestionResponseDTO;
import com.amritspring.QuoraDemo.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(
            @RequestBody QuestionRequestDTO questionRequestDTO
    ) {
        return this.questionService
                .createQuestion(questionRequestDTO)
                .doOnSuccess(
                        response -> System.out.println("Controller -> Question created successfully: " + response)
                )
                .doOnError(
                        error -> System.out.println("Controller -> Error while creating question: " + error)
                );
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id) {
        return this.questionService
                .getQuestionById(id)
                .doOnSuccess(
                        response -> System.out.println("Controller -> Question fetched successfully: " + response)
                )
                .doOnError(error -> System.out.println("Controller -> Error while fetching question: " + error));
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return this.questionService
                .getAllQuestions()
                .doOnError(error -> System.out.println("Controller -> Error while fetching questions: " + error));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id) {
        return this.questionService
                .deleteQuestionById(id)
                .doOnSuccess(
                        response -> System.out.println("Controller -> Question deleted successfully: " + response)
                )
                .doOnError(error -> System.out.println("Controller -> Error while deleting question: " + error));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestionsUsingOffsetBasedPagination(
            @RequestParam String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return this.questionService
                .searchQuestionsUsingOffsetBasedPagination(text, page, size)
                .doOnError(error -> System.out.println("Controller -> Error while searching questions: " + error));
    }
}
