package com.kenneth.remind_me.controller;

import com.kenneth.remind_me.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remind-me/api/v1")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

}
