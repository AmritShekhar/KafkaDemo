package com.amritspring.QuoraDemo.DTOs.LikeDTOs;

import com.amritspring.QuoraDemo.enums.TargetType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequestDTO {
    @NotBlank(message = "Target ID is required")
    private String targetId;
    @NotBlank(message = "Target type is required")
    private TargetType targetType;
    @NotBlank(message = "Like status is required")
    private Boolean isLike;
}
