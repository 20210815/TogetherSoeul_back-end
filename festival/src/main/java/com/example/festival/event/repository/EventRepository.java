package com.example.festival.event.repository;

import com.example.festival.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAll();

    Optional<Event> findByEventId(Integer eventId);

    // 상태랑 구 모두 입력
    List<Event> findByStateAndRegionAndTitleContainingOrLocationContaining(Integer state, String region, String keyword, String LocationKeyword);

    // 상태만 입력
    List<Event> findByStateAndTitleContainingOrLocationContaining(Integer state, String keyword, String LocationKeyword);

    //구만 입력
    List<Event> findByRegionAndTitleContainingOrLocationContaining(String region, String keyword, String LocationKeyword);

    //아무것도 입력 안 함
    List<Event> findByTitleContainingOrLocationContaining(String keyword, String LocationKeyword);

    List<Event> findByState(Integer state);

    Optional<Event> findTop1ByOrderByViewDesc();
}
