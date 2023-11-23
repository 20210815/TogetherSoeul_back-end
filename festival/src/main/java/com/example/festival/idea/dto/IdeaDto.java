package com.example.festival.idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDto {

    @Nullable
    private String title;

    @Nullable
    private String content;

    @Nullable
    private String user;

    @Nullable
    private String image;
}
