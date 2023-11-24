package com.example.festival.idea.controller;

import com.example.festival.base.projection.idea.GetIdea;
import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.service.AmazonS3Service;
import com.example.festival.idea.dto.IdeaDto;
import com.example.festival.idea.entity.Idea;
import com.example.festival.idea.service.IdeaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/idea")
public class IdeaController {

    private final AmazonS3Service amazonS3Service;
    private final IdeaService ideaService;

    // 아이디어 제안 포스트 등록
    @PostMapping("")
    public String uploadIdea(@RequestParam("idea") String ideaDto, @RequestParam("image") List<MultipartFile> multipartFiles) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ObjectMapper mapper = new ObjectMapper();
        IdeaDto mapperIdeaDTO = mapper.readValue(ideaDto, IdeaDto.class);

        List<String> imageFile = amazonS3Service.saveFiles(multipartFiles);
        mapperIdeaDTO.setImage(imageFile.toString());

        String idea = ideaService.uploadIdeaPost(mapperIdeaDTO, authentication.getName());

        return idea;
    }

    // 아이디어 제안 포스트 목록
    @GetMapping("")
    public List<GetIdea> getIdeaList() {

        List<GetIdea> list = ideaService.ideaList();

        return list;
    }

    // 아이디어 제안 포스트 상세
    @GetMapping("/detail")
    public Optional<GetIdea> getIdeaDetail(@RequestParam("ideaId") Integer ideaId) {

        Optional<GetIdea> idea = ideaService.ideaDetail(ideaId);

        return idea;
    }

    @DeleteMapping("/delete")
    public String deleteIdea(@RequestParam("ideaId") Integer ideaId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String result = ideaService.deleteIdeaPost(ideaId, authentication.getName());

        return result;
    }



}
