package com.example.festival.festival.controller;

import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.service.AmazonS3Service;
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

    private final AmazonS3Service amazonS3Service;
    private final FestivalService festivalService;

    @PostMapping("")
    public String uploadFestival(@RequestParam("festival") String festivalDTO, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        FestivalDTO mapperFestivalDTO = mapper.readValue(festivalDTO, FestivalDTO.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperFestivalDTO.setImage(imageFile);

        String festival = festivalService.uploadFestival(mapperFestivalDTO);

        return festival;
    }

    @GetMapping("")
    public List<Festival> getFestivalList() {

        List<Festival> list = festivalService.festivalList();

        return list;
    }

    @GetMapping("/{festivalId}")
    public Optional<Festival> getFestivalDetail(@PathVariable("festivalId") Integer festivalId) {

        Optional<Festival> festival = festivalService.festivalDetail(festivalId);

        return festival;
    }

    @GetMapping("/search")
    public List<Festival> searchFestival(@RequestParam("keyword") String keyword, @RequestParam("state") Integer state) {

        List<Festival> list = festivalService.searchFestival(keyword, state);

        return list;
    }

    @GetMapping("/list")
    public List<Festival> getFestivalStateList(@RequestParam("state") Integer state) {

        List<Festival> list = festivalService.festivalListState(state);

        return list;
    }

    @GetMapping("/view")
    public Optional<Festival> getTopViewFestival() {

        Optional<Festival> festival = festivalService.topViewFestival();

        return festival;
    }
}
