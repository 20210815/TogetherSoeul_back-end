package com.example.festival.event.service;

import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.Event;
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

        Event event = Event.builder()
                .title(eventDto.getTitle())
                .content(eventDto.getContent())
                .image(eventDto.getImage())
                .location(eventDto.getLocation())
                .rule(eventDto.getRule())
                .register(eventDto.getRegister())
                .startDay(eventDto.getStartDay())
                .endDay(eventDto.getEndDay())
                .resultDay(eventDto.getResultDay())
                .state(eventDto.getState())
                .view(0)
                .build();

        eventRepository.save(event);

        return "이벤트 등록 완료";
    }

    @Override
    public List<Event> eventList() {

        List<Event> list = eventRepository.findAll();

        return list;
    }

    @Override
    public Optional<Event> eventDetail(Integer eventId) {

        Optional<Event> event = eventRepository.findByEventId(eventId);

        event.get().setView(event.get().getView()+1);

        eventRepository.save(event.get());

        return event;
    }

    @Override
    public List<Event> searchEvent(String keyword, Integer state) {

        List<Event> list = eventRepository.findByStateAndTitleContainingOrLocationContaining(state, keyword, keyword);

        return list;
    }

    @Override
    public List<Event> eventStateList(Integer state) {

        List<Event> list = eventRepository.findByState(state);

        return list;
    }

    @Override
    public Optional<Event> topViewEvent() {

        Optional<Event> event = eventRepository.findTop1ByOrderByViewDesc();

        return event;
    }
}
