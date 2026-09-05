package com.kenneth.remind_me.dto.response;

import com.kenneth.remind_me.enums.TimeFrame;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReminderResponseDto {
    private UUID id;

    private String message;

    private TimeFrame timing;

    private LocalDateTime scheduledAt;

    private Integer intervalDays;

    private LocalDateTime endsAt;

    private LocalDateTime lastSentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
