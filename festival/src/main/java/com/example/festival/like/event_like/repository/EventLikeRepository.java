package com.example.festival.like.event_like.repository;

import com.example.festival.event.entity.Event;
import com.example.festival.festival.entity.Festival;
import com.example.festival.like.event_like.entity.Event_like;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventLikeRepository {
    private final EventLikeInterface eventLikeInterface;

    public EventLikeRepository(@Autowired EventLikeInterface eventLikeInterface) {
        this.eventLikeInterface = eventLikeInterface;
    }

    //좋아요 생성
    public void createEventLike(Event event, User user ) {
        Event_like eventLike = new Event_like(user, event);

        this.eventLikeInterface.save(eventLike);
    }

    //좋아요 삭제
    public void deleteEventLike(Event event, User user) {
        this.eventLikeInterface.delete(this.eventLikeInterface.findByUserAndEvent(user, event));

    }
}
