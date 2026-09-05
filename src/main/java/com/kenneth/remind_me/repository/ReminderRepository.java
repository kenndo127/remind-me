package com.kenneth.remind_me.repository;

import com.kenneth.remind_me.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
    Optional<Reminder> findByMessage(String message);
}
