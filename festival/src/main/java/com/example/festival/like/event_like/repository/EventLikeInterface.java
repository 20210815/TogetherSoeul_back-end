package com.example.festival.like.event_like.repository;

import com.example.festival.event.entity.Event;
import com.example.festival.festival.entity.Festival;
import com.example.festival.like.event_like.entity.Event_Like_id;
import com.example.festival.like.event_like.entity.Event_like;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventLikeInterface extends CrudRepository<Event_like, Event_Like_id> {
    public Event_like findByUserAndEvent(User user, Event event);
    public Integer countEvent_likeByEvent (Event event);
    public List<Integer> findByUser(User user);

    @Query(value = "select event_id from event_like " +
            "group by event_like.event_id " +
            "order by count(*) DESC;", nativeQuery = true)
    public List<Integer> findEventsOrderByLikeCount();

}
