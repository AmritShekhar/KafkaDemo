package com.amritspring.QuoraDemo.repositories;

import com.amritspring.QuoraDemo.models.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository extends ReactiveMongoRepository<Like, String> {
}
