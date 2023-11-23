package com.example.festival.like.festival_like.repository;

import com.example.festival.festival.entity.Festival;
import com.example.festival.like.festival_like.entity.Festival_like;
import com.example.festival.like.festival_like.entity.Festival_like_ID;
import com.example.festival.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FestivalLikeInterface extends CrudRepository<Festival_like, Festival_like_ID> {
    public Festival_like findByUserAndFestival(User user, Festival festival);
    public Integer countFestival_likeByFestival (Festival festival);
    public List<Integer> findByUser(User user);

    @Query(value = "select festival_id from festival_like " +
            "group by festival_like.festival_id " +
            "order by count(*) DESC;", nativeQuery = true)
    public List<Integer> findFestivalsOrderByLikeCount();


}
