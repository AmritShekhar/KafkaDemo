package com.amritspring.QuoraDemo.DTOs.LikeDTOs;

import com.amritspring.QuoraDemo.enums.TargetType;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDTO {
    private String likeId;
    private String targetId;
    private TargetType targetType;
    private Boolean isLike;
    private Instant createdAt;
}
