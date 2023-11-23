package com.example.festival.base.projection.idea;

import com.example.festival.user.entity.User;

import java.time.LocalDateTime;

public interface GetIdea {

    Integer getIdeaId();

    String getTitle();

    String getContent();

    String getImage();

    LocalDateTime getCreatedAt();

    User getUser();

    interface User{
        String getUserId();
        String getUsername();
        String getNickname();
        String getIdentify();
        String getImage();
    }
}
