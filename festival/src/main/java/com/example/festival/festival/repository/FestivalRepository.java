package com.example.festival.festival.repository;

import com.example.festival.festival.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {

    List<Festival> findAll();

    Optional<Festival> findByFestivalId(Integer festivalId);

    // 상태랑 구 모두 입력
    List<Festival> findByStateAndRegionAndTitleContainingOrLocationContaining(Integer state, String region, String keyword, String LocationKeyword);

    // 상태만 입력
    List<Festival> findByStateAndTitleContainingOrLocationContaining(Integer state, String keyword, String LocationKeyword);

    //구만 입력
    List<Festival> findByRegionAndTitleContainingOrLocationContaining(String region, String keyword, String LocationKeyword);

    //아무것도 입력 안 함
    List<Festival> findByTitleContainingOrLocationContaining(String keyword, String LocationKeyword);

    List<Festival> findByState(Integer state);

    Optional<Festival> findTop1ByOrderByViewDesc();
}
