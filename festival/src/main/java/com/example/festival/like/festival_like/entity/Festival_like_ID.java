package com.example.festival.like.festival_like.entity;

import com.example.festival.festival.entity.Festival;
import com.example.festival.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Festival_like_ID implements Serializable{

        private Integer user;
        private Integer festival;
}