package com.example.festival.event.service;

import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventService {

    String uploadEvent(EventDTO eventDTO);

    List<EventEntity> eventList();

    Optional<EventEntity> eventDetail(Integer eventId);

    List<EventEntity> searchEvent(String keyword);

}
