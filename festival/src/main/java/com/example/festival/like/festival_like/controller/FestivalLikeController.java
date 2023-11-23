package com.example.festival.like.festival_like.controller;

import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.service.FestivalService;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.like.festival_like.service.FestivalLikeService;
import com.example.festival.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festival_like")
public class FestivalLikeController {

    private final FestivalLikeService festivalLikeService;

    public FestivalLikeController(
            @Autowired FestivalLikeService festivalLikeService
    ) {
        this.festivalLikeService = festivalLikeService;
    }

    //좋아요 추가
    @PostMapping("/{festivalId}")
    public void createFestivalLike(@PathVariable("festivalId") Integer festivalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        this.festivalLikeService.createFestivalLike(festivalId, authentication.getName());
    }


    //좋아요 삭제
    @DeleteMapping("/{festivalId}")
    public void deleteFestivalLike(@PathVariable("festivalId") Integer festivalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        this.festivalLikeService.deleteFestivalLike(festivalId, authentication.getName());
    }


    //같은 글 좋아요 개수 불러오기
    @GetMapping("/{festivalId}")
    public Integer findByFestivalLikeAll(@PathVariable("festivalId") Integer festivalId) {
        return this.festivalLikeService.findByFestivalLikeAll(festivalId);
    }


    //축제 글 좋아요 순위로 나열
    @GetMapping("/top")
    public List<Festival> rankByFestivalLike() {
        return this.festivalLikeService.rankByFestival();
    }
}
