package com.example.festival.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    @Nullable
    private Integer eventId;

    @Nullable
    private String title;

    @Nullable
    private String content;

    @Nullable
    private String image;

    @Nullable
    private String location;

    @Nullable
    private String rule;

    @Nullable
    private String register;

    @Nullable
    private String startDay;

    @Nullable
    private String endDay;

    @Nullable
    private String resultDay;

    @Nullable
    private Integer state;

}
