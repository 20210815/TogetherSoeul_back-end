package com.example.festival.like.festival_like.repository;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.repository.FestivalRepository;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FestivalLikeRepository {
    private final FestivalLikeInterface festivalLikeInterface;
    private final AuthRepository authRepository;

    public FestivalLikeRepository(
            @Autowired FestivalLikeInterface festivalLikeInterface,
            @Autowired AuthRepository authRepository
    ) {
        this.festivalLikeInterface = festivalLikeInterface;
        this.authRepository = authRepository;
    }


    //좋아요 생성
    public void createFestivalLike(Festival festival, User user ) {
        Festival_like festivalLike = new Festival_like(user, festival);

        this.festivalLikeInterface.save(festivalLike);
    }

    //좋아요 삭제
    public void deleteFestivalLike(Festival festival, User user) {
        this.festivalLikeInterface.delete(this.festivalLikeInterface.findByUserAndFestival(user, festival));

    }


    //유저별 축제글 좋아요 불러오기







}
