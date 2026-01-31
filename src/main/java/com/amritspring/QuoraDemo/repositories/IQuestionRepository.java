package com.amritspring.QuoraDemo.repositories;

import com.amritspring.QuoraDemo.models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends ReactiveMongoRepository<Question, String> {
}
