package com.kenneth.remind_me.service.impl;

import com.kenneth.remind_me.dto.request.PersonRequestDto;
import com.kenneth.remind_me.dto.response.PersonResponseDto;
import com.kenneth.remind_me.dto.response.ReminderResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;
import com.kenneth.remind_me.entity.Person;
import com.kenneth.remind_me.exceptions.DuplicatePersonFoundException;
import com.kenneth.remind_me.exceptions.ResourceNotFoundException;
import com.kenneth.remind_me.repository.PersonRepository;
import com.kenneth.remind_me.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public ResponseWrapper<PersonResponseDto> createPerson(PersonRequestDto personRequestDto) {
        if(personRepository.findByName(personRequestDto.getName()).isPresent())
            throw new DuplicatePersonFoundException("This Person already exists");

        Person person = Person.builder()
                            .name(personRequestDto.getName())
                            .build();

        Person savedPerson = personRepository.save(person);

        return buildResponseWrapper(buildPersonResponseDto(savedPerson), "Successfully created a person");
    }

    @Override
    @Transactional
    public ResponseWrapper<PersonResponseDto> updatePerson(UUID id, PersonRequestDto personRequestDto) {

        Person originalPerson = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This person does not exist"));

        personRepository.findByName(personRequestDto.getName())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new DuplicatePersonFoundException("This person exists already, try another name!");
                });

        originalPerson.setName(personRequestDto.getName());

        Person savedPerson = personRepository.save(originalPerson);

        return buildResponseWrapper(buildPersonResponseDto(savedPerson), "Successfully updated a person");
    }

    @Override
    public ResponseWrapper<List<PersonResponseDto>> findAllPerson() {
        List<Person> allPersons = personRepository.findAll();

        List<PersonResponseDto> allPersonslist = allPersons.stream()
                .map(this::buildPersonResponseDto)
                .toList();

        return buildResponseWrapper(allPersonslist, "All persons returned successfully");
    }

    @Override
    public ResponseWrapper<PersonResponseDto> findPersonById(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("This Person does not exist"));

        return buildResponseWrapper(buildPersonResponseDto(person), "Person retrieved successfully!");
    }

    @Override
    public void deletePerson(UUID id) {
        Person personToDelete = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("This person does not exist"));
        personRepository.delete(personToDelete);
    }

    private PersonResponseDto buildPersonResponseDto(Person savedPerson){

        List<ReminderResponseDto> reminders = savedPerson.getReminders() == null ?
                List.of()
                : savedPerson.getReminders().stream()
                  .map(reminder -> ReminderResponseDto.builder()
                                   .id(reminder.getId())
                                   .message(reminder.getMessage())
                                   .timing(reminder.getTiming())
                                   .scheduledAt(reminder.getScheduledAt())
                                   .intervalDays(reminder.getIntervalDays())
                                   .endsAt(reminder.getEndsAt())
                                   .lastSentAt(reminder.getLastSentAt())
                                   .createdAt(reminder.getCreatedAt())
                                   .updatedAt(reminder.getUpdatedAt())
                                   .build())
                  .toList();

        return PersonResponseDto.builder()
                .id(savedPerson.getId())
                .name(savedPerson.getName())
                .reminders(reminders)
                .createdAt(savedPerson.getCreatedAt())
                .updatedAt(savedPerson.getUpdatedAt())
                .build();
    }

    private <T> ResponseWrapper<T> buildResponseWrapper(T data, String response){
        return ResponseWrapper.<T>builder()
                .data(data)
                .response(response)
                .build();
    }
}
