package com.kenneth.remind_me.dto.request;

import com.kenneth.remind_me.enums.TimeFrame;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReminderRequestDto {
    @NotBlank(message = "A reminder cannot have an empty message")
    private String message;

    @NotNull(message = "Reminder must have a type")
    private TimeFrame timing;

    @Future(message = "Must schedule at a future date")
    private LocalDateTime scheduledAt;

    private Integer intervalDays;

    @FutureOrPresent(message = "Must end at a future date")
    private LocalDateTime endsAt;
}
