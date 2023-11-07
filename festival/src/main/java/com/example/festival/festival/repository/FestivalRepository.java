package com.example.festival.festival.repository;

import com.example.festival.base.projection.festival.GetFestivalList;
import com.example.festival.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Integer> {

    List<FestivalEntity> findAll();

    List<FestivalEntity> findByTitleContainingOrLocationContaining(String keyword, String lkeyword);
}
