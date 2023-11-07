package com.example.festival.event.service;

import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.EventEntity;
import com.example.festival.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Override
    public String uploadEvent(EventDTO eventDto) {

        EventEntity event = EventEntity.builder()
                .title(eventDto.getTitle())
                .content(eventDto.getContent())
                .image(eventDto.getImage())
                .location(eventDto.getLocation())
                .rule(eventDto.getRule())
                .register(eventDto.getRegister())
                .ing(eventDto.getIng())
                .startDay(eventDto.getStartDay())
                .endDay(eventDto.getEndDay())
                .resultDay(eventDto.getResultDay())
                .build();

        eventRepository.save(event);

        return "이벤트 등록 완료";
    }

    @Override
    public List<EventEntity> eventList() {

        List<EventEntity> list = eventRepository.findAll();

        return list;
    }

    @Override
    public Optional<EventEntity> eventDetail(Integer eventId) {

        Optional<EventEntity> event = eventRepository.findByEventId(eventId);

        return event;
    }

    @Override
    public List<EventEntity> searchEvent(String keyword) {

        List<EventEntity> list = eventRepository.findByTitleContainingOrLocationContaining(keyword, keyword);

        return list;
    }



}
