package com.example.festival.event.service;

import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    String uploadEvent(EventDTO eventDTO);

    List<Event> eventList();

    Optional<Event> eventDetail(Integer eventId);

    List<Event> searchEvent(String keyword, Integer state, String region);

    List<Event> eventStateList(Integer state);

    Optional<Event> topViewEvent();
}
