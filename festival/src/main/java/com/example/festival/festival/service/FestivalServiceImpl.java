package com.example.festival.festival.service;

import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService{

    private final FestivalRepository festivalRepository;

    @Override
    public String uploadFestival(FestivalDTO festivalDto){

        Festival festival = Festival.builder()
                .title(festivalDto.getTitle())
                .content(festivalDto.getContent())
                .image(festivalDto.getImage())
                .location(festivalDto.getLocation())
                .startDay(festivalDto.getStartDay())
                .endDay(festivalDto.getEndDay())
                .state(festivalDto.getState())
                .view(0)
                .build();

        festivalRepository.save(festival);

        return "축제 등록 완료";
    }

    @Override
    public List<Festival> festivalList() {

        List<Festival> festivalList = festivalRepository.findAll();

        return festivalList;
    }

    @Override
    public List<Festival> festivalIngList() {

        List<Festival> festivalList = festivalRepository.findAll();

        return festivalList;
    }

    @Override
    public Optional<Festival> festivalDetail(Integer festivalId) {

        Optional<Festival> festival = festivalRepository.findByFestivalId(festivalId);

        festival.get().setView(festival.get().getView() + 1);

        festivalRepository.save(festival.get());

        return festival;
    }

    @Override
    public List<Festival> searchFestival(String keyword, Integer state) {

        List<Festival> festivalList = festivalRepository.findByStateAndTitleContainingOrLocationContaining(state, keyword, keyword);

        return festivalList;
    }

    @Override
    public List<Festival> festivalListState(Integer state) {

        List<Festival> festivalList = festivalRepository.findByState(state);

        return festivalList;
    }

    @Override
    public Optional<Festival> topViewFestival() {

        Optional<Festival> festival = festivalRepository.findTop1ByOrderByViewDesc();

        return festival;
    }
}
