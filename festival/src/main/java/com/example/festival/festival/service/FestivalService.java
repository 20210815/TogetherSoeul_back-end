package com.example.festival.festival.service;

import com.example.festival.base.projection.festival.GetFestivalList;
import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.FestivalEntity;

import java.util.List;
import java.util.Optional;


public interface FestivalService {

    String uploadFestival(FestivalDTO festivalDto);

    List<FestivalEntity> festivalList();

    Optional<FestivalEntity> festivalDetail(Integer festivalId);

    List<FestivalEntity> searchFestival(String keyword);
}
