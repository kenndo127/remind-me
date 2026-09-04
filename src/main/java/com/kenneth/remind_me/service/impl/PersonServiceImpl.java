package com.kenneth.remind_me.service.impl;

import com.kenneth.remind_me.dto.request.PersonRequestDto;
import com.kenneth.remind_me.dto.response.PersonResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;
import com.kenneth.remind_me.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public ResponseWrapper<PersonResponseDto> createPerson(PersonRequestDto person) {
        return null;
    }

    @Override
    public ResponseWrapper<PersonResponseDto> updatePerson(UUID id, PersonRequestDto person) {
        return null;
    }

    @Override
    public ResponseWrapper<List<PersonResponseDto>> findAllPerson() {
        return null;
    }

    @Override
    public ResponseWrapper<PersonResponseDto> findPersonById() {
        return null;
    }

    @Override
    public void deletePerson(UUID id) {

    }
}
