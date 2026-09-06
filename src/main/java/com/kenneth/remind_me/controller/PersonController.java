package com.kenneth.remind_me.controller;

import com.kenneth.remind_me.dto.request.PersonRequestDto;
import com.kenneth.remind_me.dto.response.PersonResponseDto;
import com.kenneth.remind_me.dto.response.ResponseWrapper;
import com.kenneth.remind_me.service.PersonService;
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
public class PersonController {

    private final PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<ResponseWrapper<PersonResponseDto>> createPerson(
            @Valid @RequestBody PersonRequestDto person
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createPerson(person));
    }

    @GetMapping("/persons")
    public ResponseEntity<ResponseWrapper<List<PersonResponseDto>>> getAllPersons(){
        return ResponseEntity.ok(personService.findAllPerson());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<ResponseWrapper<PersonResponseDto>> getPersonById(
            @PathVariable UUID id
    ){
        return ResponseEntity.ok(personService.findPersonById(id));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<ResponseWrapper<PersonResponseDto>> updatePerson(
            @Valid @RequestBody PersonRequestDto person,
            @PathVariable UUID id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.updatePerson(id, person));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable UUID id
    ){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
//Todo: Add the exception that handles the entity validations in the global exeption
//todo: handler