package com.example.festival.idea.service;

import com.example.festival.base.projection.idea.GetIdea;
import com.example.festival.idea.dto.IdeaDto;
import com.example.festival.idea.entity.Idea;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    String uploadIdeaPost(IdeaDto ideaDto, String userId);

    List<GetIdea> ideaList();

    Optional<GetIdea> ideaDetail(Integer ideaId);

}
