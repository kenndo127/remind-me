package com.kenneth.remind_me.dto.response;

import com.kenneth.remind_me.entity.Reminder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@ToString
public class PersonResponseDto {
    private UUID id;

    private String name;

    private List<ReminderResponseDto> reminders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
