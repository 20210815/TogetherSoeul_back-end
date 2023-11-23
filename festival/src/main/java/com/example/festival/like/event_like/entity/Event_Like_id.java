package com.example.festival.like.event_like.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Event_Like_id implements Serializable{

        private Integer user;
        private Integer event;
}