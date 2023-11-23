package com.example.festival.like.event_like.service;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.event.entity.Event;
import com.example.festival.event.repository.EventRepository;
import com.example.festival.festival.entity.Festival;
import com.example.festival.like.event_like.entity.Event_like;
import com.example.festival.like.event_like.repository.EventLikeInterface;
import com.example.festival.like.event_like.repository.EventLikeRepository;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventLikeService {
    private final EventLikeRepository eventLikeRepository;
    private final EventLikeInterface eventLikeInterface;
    private final AuthRepository authRepository;
    private final EventRepository eventRepository;

    public EventLikeService(@Autowired EventLikeRepository eventLikeRepository,
                            @Autowired EventLikeInterface eventLikeInterface,
                            @Autowired AuthRepository authRepository,
                            @Autowired EventRepository eventRepository) {
        this.eventLikeInterface = eventLikeInterface;
        this.eventLikeRepository = eventLikeRepository;
        this.authRepository = authRepository;
        this.eventRepository = eventRepository;
    }

    public void createEventLike(Integer eventId, String identify){
        User user = this.authRepository.findByIdentify(identify);
        Event event = this.eventRepository.findByEventId(eventId).get();

        this.eventLikeRepository.createEventLike(event, user);
    }

    public void deleteEventLike(Integer eventId, String identify){
        User user = this.authRepository.findByIdentify(identify);
        Event event = this.eventRepository.findByEventId(eventId).get();

        this.eventLikeRepository.deleteEventLike(event, user);
    }

//    public List<User> findByFestivalLikeAll(Integer festivalId) {
//        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();
//        List<Festival_like> festivalLikes = this.festivalLikeRepository.findByFestivalAll(festival);
//
//        List<User> userList = new ArrayList<>();
//
//        for(Festival_like festivalLike: festivalLikes) {
//            User user = festivalLike.getUser();
//            userList.add(user);
//        }
//
//        return userList;
//    }


    public Integer findByEventLikeAll(Integer eventId) {
        Event event = this.eventRepository.findByEventId(eventId).get();

        return this.eventLikeInterface.countEvent_likeByEvent(event);
    }

    //축제 글 좋아요 순위로 나열
    public List<Event> rankByFestival() {
        List<Integer> festivals = this.eventLikeInterface.findEventsOrderByLikeCount();
        List<Event> festivalList = new ArrayList<>();

        if (festivals.size() >= 3) {
            festivals = festivals.subList(0, 3);
        } else {
            festivals = festivals; // Return all festivals if the total count is less than 3
        }

        for (Integer festival_id: festivals) {
            Event event = this.eventRepository.findByEventId(festival_id).get();
            festivalList.add(event);
        }

        return festivalList;
    }

    public Integer checkEventLike(String userId, Integer eventId) {
        User user = this.authRepository.findByIdentify(userId);
        Event event = this.eventRepository.findByEventId(eventId).get();

        Event_like like = eventLikeInterface.findByUserAndEvent(user, event);

        if(like!=null){
            return 1;
        }

        return 0;
    }

    //좋아요 이벤트 목록 나열
    public List<Event> likeEventList(String identify) {
        User user = this.authRepository.findByIdentify(identify);
        List<Event> events = new ArrayList<>();

        List<Event_like> eventLikes = this.eventLikeInterface.findByUser(user);
        for(Event_like eventLike : eventLikes) {
            Event event = eventLike.getEvent();
            events.add(event);
        }

        return events;
    }
}
