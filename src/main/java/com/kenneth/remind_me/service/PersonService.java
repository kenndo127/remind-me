package com.kenneth.remind_me.service;

import com.kenneth.remind_me.dto.request.PersonRequestDto;
import com.kenneth.remind_me.dto.response.PersonResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    ResponseWrapper<PersonResponseDto> createPerson(PersonRequestDto person);
    ResponseWrapper<PersonResponseDto> updatePerson(UUID id, PersonRequestDto person);
    ResponseWrapper<List<PersonResponseDto>> findAllPerson();
    ResponseWrapper<PersonResponseDto> findPersonById();
    void deletePerson(UUID id);
}