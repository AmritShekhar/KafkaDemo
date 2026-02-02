package com.amritspring.QuoraDemo.services;

import com.amritspring.QuoraDemo.DTOs.LikeDTOs.LikeRequestDTO;
import com.amritspring.QuoraDemo.DTOs.LikeDTOs.LikeResponseDTO;
import com.amritspring.QuoraDemo.enums.TargetType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {
    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Flux<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, TargetType targetType);
    Flux<LikeResponseDTO> countDislikesByTargetIdAndTargetType(String targetId, TargetType targetType);
}
