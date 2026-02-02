package com.amritspring.QuoraDemo.models;

import com.amritspring.QuoraDemo.enums.TargetType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "likes")
public class Like extends BaseDocument {

    private String targetId;
    private TargetType targetType;
    private Boolean isLike;
}
