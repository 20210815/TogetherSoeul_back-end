package com.example.festival.like.festival_like.service;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.repository.FestivalRepository;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.like.festival_like.repository.FestivalLikeInterface;
import com.example.festival.like.festival_like.repository.FestivalLikeRepository;
import com.example.festival.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FestivalLikeService {
    private final FestivalLikeRepository festivalLikeRepository;
    private final FestivalLikeInterface festivalLikeInterface;
    private final FestivalRepository festivalRepository;
    private final AuthRepository authRepository;

    public FestivalLikeService(
            @Autowired FestivalLikeRepository festivalLikeRepository,
            @Autowired FestivalLikeInterface festivalLikeInterface,
            @Autowired AuthRepository authRepository,
            @Autowired FestivalRepository festivalRepository
    ) {
        this.festivalLikeInterface = festivalLikeInterface;
        this.festivalLikeRepository = festivalLikeRepository;
        this.authRepository = authRepository;
        this.festivalRepository = festivalRepository;
    }

    public void createFestivalLike(Integer festivalId, String identify){
        User user = this.authRepository.findByIdentify(identify);
        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();

        this.festivalLikeRepository.createFestivalLike(festival, user);
    }

    public void deleteFestivalLike(Integer festivalId, String identify){
        User user = this.authRepository.findByIdentify(identify);
        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();

        this.festivalLikeRepository.deleteFestivalLike(festival, user);
    }

//    public List<User> findByFestivalLikeAll(Integer festivalId) {
//        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();
//        List<Festival_like> festivalLikes = this.festivalLikeRepository.findByFestivalAll(festival);
//
//        List<User> userList = new ArrayList<>();
//
//        for(Festival_like festivalLike: festivalLikes) {
//            User user = festivalLike.getUser();
//            userList.add(user);
//        }
//
//        return userList;
//    }


    public Integer findByFestivalLikeAll(Integer festivalId) {
        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();

        return this.festivalLikeInterface.countFestival_likeByFestival(festival);
    }

    //축제 글 좋아요 순위로 나열
    public List<Festival> rankByFestival() {
        List<Integer> festivals = this.festivalLikeInterface.findFestivalsOrderByLikeCount();
        List<Festival> festivalList = new ArrayList<>();

        if (festivals.size() >= 3) {
            festivals = festivals.subList(0, 3);
        } else {
            festivals = festivals; // Return all festivals if the total count is less than 3
        }

        for (Integer festival_id: festivals) {
            Festival festival = this.festivalRepository.findByFestivalId(festival_id).get();
            festivalList.add(festival);
        }

        return festivalList;
    }

    public Integer checkFestivalLike(String userId, Integer festivalId) {
        User user = this.authRepository.findByIdentify(userId);
        Festival festival = this.festivalRepository.findByFestivalId(festivalId).get();

        Festival_like like = festivalLikeInterface.findByUserAndFestival(user, festival);

        if(like!=null){
            return 1;
        }

        return 0;
    }

}
