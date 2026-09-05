package com.kenneth.remind_me.service;

import com.kenneth.remind_me.dto.request.ReminderRequestDto;
import com.kenneth.remind_me.dto.response.ReminderResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReminderService {
    ResponseWrapper<ReminderResponseDto> createReminder(UUID personId, ReminderRequestDto reminderRequestDto);
    ResponseWrapper<ReminderResponseDto> updateReminder(UUID reminderID, ReminderRequestDto reminderRequestDto);
    ResponseWrapper<List<ReminderResponseDto>> findAllReminderByPerson(UUID personId);
    ResponseWrapper<ReminderResponseDto> findReminderById(UUID reminderId);
    void deleteReminder(UUID reminderId);
}
