package com.example.festival.idea.service;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.base.projection.idea.GetIdea;
import com.example.festival.idea.dto.IdeaDto;
import com.example.festival.idea.entity.Idea;
import com.example.festival.idea.repository.IdeaRepository;
import com.example.festival.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdeaServiceImpl implements IdeaService{

    private final IdeaRepository ideaRepository;
    private final AuthRepository authRepository;

    @Override
    public String uploadIdeaPost(IdeaDto ideaDto, String userId) {
        User user = authRepository.findByIdentify(userId);

        Idea idea = Idea.builder()
                .title(ideaDto.getTitle())
                .content(ideaDto.getContent())
                .user(user)
                .image(ideaDto.getImage())
                .build();
        ideaRepository.save(idea);

        return "아이디어 제안 포스트 등록 성공";
    }

    @Override
    public List<GetIdea> ideaList() {

        List<GetIdea> list = ideaRepository.findAllProjectedBy();

        return list;
    }

    @Override
    public Optional<GetIdea> ideaDetail(Integer ideaId) {

        Optional<GetIdea> idea = ideaRepository.findByIdeaId(ideaId);

        return idea;
    }


}
