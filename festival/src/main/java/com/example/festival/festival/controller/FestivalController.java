package com.example.festival.festival.controller;

import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.FestivalEntity;
import com.example.festival.festival.service.FestivalService;
import com.example.festival.festival.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/festival")
public class FestivalController {

    private final UploadFileService uploadFileService;
    private final FestivalService festivalService;

    @PostMapping("")
    public String uploadFestival(@RequestParam("festival") String festivalDTO, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        FestivalDTO mapperFestivalDTO = mapper.readValue(festivalDTO, FestivalDTO.class);

        String imageFile = uploadFileService.storeFile(multipartFile);
        mapperFestivalDTO.setImage(imageFile);

        String festival = festivalService.uploadFestival(mapperFestivalDTO);

        return festival;
    }

    @GetMapping("")
    public List<FestivalEntity> getFestivalList() {

        List<FestivalEntity> list = festivalService.festivalList();

        return list;
    }

    @GetMapping("/{festivalId}")
    public Optional<FestivalEntity> getFestivalDetail(@PathVariable("festivalId") Integer festivalId) {

        Optional<FestivalEntity> festival = festivalService.festivalDetail(festivalId);

        return festival;
    }

    @GetMapping("/search")
    public List<FestivalEntity> searchFestival(@RequestParam("keyword") String keyword) {

        List<FestivalEntity> list = festivalService.searchFestival(keyword);

        return list;
    }
}
