package com.example.festival.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FestivalDTO {

    @Nullable
    private String title;

    @Nullable
    private String content;

    @Nullable
    private String image;

    @Nullable
    private String location;

    @Nullable
    private String startDay;

    @Nullable
    private String endDay;

    @Nullable
    private Integer state;

    @Nullable
    private Integer view;


}
