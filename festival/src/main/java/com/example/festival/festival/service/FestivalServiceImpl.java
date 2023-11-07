package com.example.festival.festival.service;

import com.example.festival.base.projection.festival.GetFestivalList;
import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.FestivalEntity;
import com.example.festival.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService{

    private final FestivalRepository festivalRepository;

    @Override
    public String uploadFestival(FestivalDTO festivalDto){

        FestivalEntity festival = FestivalEntity.builder()
                .title(festivalDto.getTitle())
                .content(festivalDto.getContent())
                .image(festivalDto.getImage())
                .location(festivalDto.getLocation())
                .startDay(festivalDto.getStartDay())
                .endDay(festivalDto.getEndDay())
                .build();

        festivalRepository.save(festival);

        return "축제 등록 완료";
    }

    @Override
    public List<FestivalEntity> festivalList() {

        List<FestivalEntity> festivalList = festivalRepository.findAll();

        return festivalList;
    }

    @Override
    public Optional<FestivalEntity> festivalDetail(Integer festivalId) {

        Optional<FestivalEntity> festival = festivalRepository.findByFestivalId(festivalId);

        return festival;
    }

    @Override
    public List<FestivalEntity> searchFestival(String keyword) {

        List<FestivalEntity> festivalList = festivalRepository.findByTitleContainingOrLocationContaining(keyword, keyword);

        return festivalList;
    }
}
