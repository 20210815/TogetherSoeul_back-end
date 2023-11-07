package com.example.festival.event.repository;

import com.example.festival.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findAll();

    Optional<EventEntity> findByEventId(Integer eventId);

    List<EventEntity> findByTitleContainingOrLocationContaining(String keyword, String LocationKeyword);
}
