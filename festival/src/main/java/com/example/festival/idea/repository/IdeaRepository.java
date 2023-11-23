package com.example.festival.idea.repository;

import com.example.festival.base.projection.idea.GetIdea;
import com.example.festival.idea.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {

    List<GetIdea> findAllProjectedBy();

    Optional<GetIdea> findByIdeaId(Integer integer);
}
