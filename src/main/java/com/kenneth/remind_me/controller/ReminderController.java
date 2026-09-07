package com.kenneth.remind_me.controller;

import com.kenneth.remind_me.dto.request.ReminderRequestDto;
import com.kenneth.remind_me.dto.response.ReminderResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;
import com.kenneth.remind_me.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("remind-me/api/v1")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping("/persons/{personId}/reminders")
    public ResponseEntity<ResponseWrapper<ReminderResponseDto>> createReminder(
            @Valid @RequestBody ReminderRequestDto reminderRequestDto, @PathVariable UUID personId
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(reminderService.createReminder(personId, reminderRequestDto));
    }

    @GetMapping("/persons/{personId}/reminders")
    public ResponseEntity<ResponseWrapper<List<ReminderResponseDto>>> getAllReminders(
            @PathVariable UUID personId
    ){
        return ResponseEntity.ok(reminderService.findAllReminderByPerson(personId));
    }

    @GetMapping("/reminders/{reminderId}")
    public ResponseEntity<ResponseWrapper<ReminderResponseDto>> getReminderById(
            @PathVariable UUID reminderId
    ){
        return ResponseEntity.ok(reminderService.findReminderById(reminderId));
    }

    @PutMapping("/reminders/{reminderId}")
    public ResponseEntity<ResponseWrapper<ReminderResponseDto>> updateReminder(
            @PathVariable UUID reminderId,
            @Valid @RequestBody ReminderRequestDto reminderRequestDto
    ){
        return ResponseEntity.ok(reminderService.updateReminder(reminderId, reminderRequestDto));
    }

    @DeleteMapping("/reminders/{reminderId}")
    public ResponseEntity<Void> deleteReminder(
            @PathVariable UUID reminderId
    ){
        reminderService.deleteReminder(reminderId);
        return ResponseEntity.noContent().build();
    }
}
