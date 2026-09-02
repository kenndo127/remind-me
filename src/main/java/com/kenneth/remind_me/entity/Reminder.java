package com.kenneth.remind_me.entity;

import com.kenneth.remind_me.enums.TimeFrame;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "A reminder cannot have an empty message")
    private String message;

    @NotNull(message = "Reminder must have a type")
    @Enumerated(EnumType.STRING) //Used by JPA to map Enums to Tables
    private TimeFrame timing;

    @Future(message = "Must schedule at a future date")
    private LocalDateTime scheduledAt;

    private Integer interval;

    @FutureOrPresent(message = "Must end at a future date")
    private LocalDateTime endsAt;

    private LocalDateTime lastSentAt;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
