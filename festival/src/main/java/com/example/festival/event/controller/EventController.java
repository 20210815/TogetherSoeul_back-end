package com.example.festival.event.controller;


import com.example.festival.event.dto.EventDTO;
import com.example.festival.event.entity.Event;
import com.example.festival.event.service.EventService;
import com.example.festival.festival.service.AmazonS3Service;
import com.example.festival.festival.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final AmazonS3Service amazonS3Service;
    private final EventService eventService;

    @PostMapping("")
    public String uploadEvent(@RequestParam("event") String eventDTO, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        EventDTO mapperEventDTO = mapper.readValue(eventDTO, EventDTO.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperEventDTO.setImage(imageFile);

        String event = eventService.uploadEvent(mapperEventDTO);

        return event;
    }

    @GetMapping("")
    public List<Event> getEventList() {

        List<Event> list = eventService.eventList();

        return list;
    }

    @GetMapping("/{eventId}")
    public Optional<Event> getEventDetail(@PathVariable("eventId") Integer eventId) {

        Optional<Event> event = eventService.eventDetail(eventId);

        return event;
    }

    @GetMapping("/search")
    public List<Event> searchEvent(@RequestParam("keyword") String keyword, @RequestParam("state") Integer state) {

        List<Event> list = eventService.searchEvent(keyword, state);

        return list;
    }

    @GetMapping("/list")
    public List<Event> getEventStateList(@RequestParam("state") Integer state) {

        List<Event> list = eventService.eventStateList(state);

        return list;
    }

    @GetMapping("/view")
    public Optional<Event> getTopViewEvent() {

        Optional<Event> event = eventService.topViewEvent();

        return event;
    }
}
