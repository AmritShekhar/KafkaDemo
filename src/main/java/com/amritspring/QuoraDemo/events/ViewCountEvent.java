package com.amritspring.QuoraDemo.events;

import com.amritspring.QuoraDemo.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCountEvent {

    private String targetId;
    private TargetType targetType;
    private Instant timestamp;
}
