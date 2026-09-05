package com.kenneth.remind_me.service.impl;

import com.kenneth.remind_me.dto.request.ReminderRequestDto;
import com.kenneth.remind_me.dto.response.ReminderResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;
import com.kenneth.remind_me.entity.Person;
import com.kenneth.remind_me.entity.Reminder;
import com.kenneth.remind_me.exceptions.ResourceNotFoundException;
import com.kenneth.remind_me.repository.PersonRepository;
import com.kenneth.remind_me.repository.ReminderRepository;
import com.kenneth.remind_me.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public ResponseWrapper<ReminderResponseDto> createReminder(UUID personId, ReminderRequestDto reminderRequestDto) {

        Person person = personRepository.findById(personId)
                .orElseThrow(()-> new ResourceNotFoundException
                        ("This Person does not exist. Can't create a reminder without a person"));

        Reminder reminder = Reminder.builder()
                .message(reminderRequestDto.getMessage())
                .timing(reminderRequestDto.getTiming())
                .scheduledAt(reminderRequestDto.getScheduledAt())
                .intervalDays(reminderRequestDto.getIntervalDays())
                .endsAt(reminderRequestDto.getEndsAt())
                .person(person)
                .build();

        Reminder savedReminder = reminderRepository.save(reminder);

        return buildResponseWrapper(buildReminderResponseDto(savedReminder), "Reminder created successfully");
    }

    @Override
    public ResponseWrapper<ReminderResponseDto> updateReminder(UUID reminderId, ReminderRequestDto reminderRequestDto) {

        Reminder reminderToBeUpdated = reminderRepository.findById(reminderId)
                .orElseThrow(()-> new ResourceNotFoundException("This reminder does not exist!"));

        reminderToBeUpdated.setMessage(reminderRequestDto.getMessage());

        Reminder savedReminder = reminderRepository.save(reminderToBeUpdated);

        return buildResponseWrapper(buildReminderResponseDto(savedReminder), "Reminder updated successfully");
    }

    @Override
    @Transactional
    public ResponseWrapper<List<ReminderResponseDto>> findAllReminderByPerson(UUID personId) {
        //fetch the person with the id
        //fetch the reminders attached to the person
        //map it to the reminder response dto
        //return the response wrapper

        Person personWithReminders = personRepository.findById(personId)
                .orElseThrow(()-> new ResourceNotFoundException("This person does not exist!"));

        List<Reminder> reminders = personWithReminders.getReminders();

        List<ReminderResponseDto> remindersDto = reminders
                .stream().map(this::buildReminderResponseDto)
                .toList();

        return buildResponseWrapper(remindersDto, "Reminders returned successfully!");
    }

    @Override
    public ResponseWrapper<ReminderResponseDto> findReminderById(UUID reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()-> new ResourceNotFoundException("This reminder does not exist!"));

        return buildResponseWrapper(buildReminderResponseDto(reminder), "Reminder returned successfully");
    }


    @Override
    public void deleteReminder(UUID id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This Reminder does not exist!"));

        reminderRepository.delete(reminder);
    }

    private ReminderResponseDto buildReminderResponseDto(Reminder savedReminder){
        return ReminderResponseDto.builder()
                .id(savedReminder.getId())
                .message(savedReminder.getMessage())
                .timing(savedReminder.getTiming())
                .scheduledAt(savedReminder.getScheduledAt())
                .intervalDays(savedReminder.getIntervalDays())
                .endsAt(savedReminder.getEndsAt())
                .lastSentAt(savedReminder.getLastSentAt())
                .createdAt(savedReminder.getCreatedAt())
                .updatedAt(savedReminder.getUpdatedAt())
                .build();
    }

    private <T> ResponseWrapper<T> buildResponseWrapper(T data, String response){
        return ResponseWrapper.<T>builder()
                .data(data)
                .response(response)
                .build();
    }
}
