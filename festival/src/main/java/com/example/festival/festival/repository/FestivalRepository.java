package com.example.festival.festival.repository;

import com.example.festival.festival.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {

    List<Festival> findAll();

    Optional<Festival> findByFestivalId(Integer festivalId);

    List<Festival> findByTitleContainingOrLocationContaining(String keyword, String LocationKeyword);

    List<Festival> findByStateAndTitleContainingOrLocationContaining(Integer state, String keyword, String LocationKeyword);

    List<Festival> findByState(Integer state);
}
