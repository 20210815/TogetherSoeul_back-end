package com.example.festival.like.event_like.controller;


import com.example.festival.event.entity.Event;
import com.example.festival.festival.entity.Festival;
import com.example.festival.like.event_like.service.EventLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.EndElement;
import java.util.List;

@RestController
@RequestMapping("/api/event_like")
public class EventLikeController {
    private final EventLikeService eventLikeService;

    public EventLikeController(
            @Autowired EventLikeService eventLikeService
    ) {
        this.eventLikeService = eventLikeService;
    }

    //좋아요 추가
    @PostMapping("/{eventId}")
    public void createEventLike(@PathVariable("eventId") Integer eventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        this.eventLikeService.createEventLike(eventId, authentication.getName());
    }


    //좋아요 삭제
    @DeleteMapping("/{eventId}")
    public void deleteEventLike(@PathVariable("eventId") Integer eventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        this.eventLikeService.deleteEventLike(eventId, authentication.getName());
    }


    //같은 글 좋아요 개수 불러오기
    @GetMapping("/{eventId}")
    public Integer findByEventLikeAll(@PathVariable("eventId") Integer eventId) {
        return this.eventLikeService.findByEventLikeAll(eventId);
    }


    //축제 글 좋아요 순위로 나열
    @GetMapping("/top")
    public List<Event> rankByEventLike() {
        return this.eventLikeService.rankByFestival();
    }

}
