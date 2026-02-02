package com.amritspring.QuoraDemo.repositories;

import com.amritspring.QuoraDemo.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends ReactiveMongoRepository<Answer, String> {
}
