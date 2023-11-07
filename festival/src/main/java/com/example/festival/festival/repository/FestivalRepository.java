package com.example.festival.festival.repository;

import com.example.festival.base.projection.festival.GetFestivalList;
import com.example.festival.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Integer> {

    List<FestivalEntity> findAll();

    Optional<FestivalEntity> findByFestivalId(Integer festivalId);

    List<FestivalEntity> findByTitleContainingOrLocationContaining(String keyword, String LocationKeyword);
}
