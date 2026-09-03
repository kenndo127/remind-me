package com.kenneth.remind_me.dto.response;

import com.kenneth.remind_me.entity.Reminder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PersonResponseDto {
    private UUID id;

    private String name;

    private List<Reminder> reminders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
