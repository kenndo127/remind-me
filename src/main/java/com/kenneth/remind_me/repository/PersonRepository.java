package com.kenneth.remind_me.repository;

import com.kenneth.remind_me.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID>{
    @Override
    Optional<Person> findById(UUID uuid);
}
