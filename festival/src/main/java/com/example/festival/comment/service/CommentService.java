package com.example.festival.comment.service;


import com.example.festival.comment.dto.CommentDto;
import com.example.festival.comment.repository.CommentRepository;
import com.example.festival.comment.repository.CommentRepositoryInterface;
import com.example.festival.partner.dto.PartnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(@Autowired CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void commentCreate(String identify, Integer partnerId, CommentDto commentDto) {
        this.commentRepository.commentCreate(identify, partnerId, commentDto);
    }

    public List<CommentDto> commentReadAllByPartner(Integer partnerId) {
        return this.commentRepository.commentReadAllByPartner(partnerId);
    }

    public String commentUpdate(Integer commentId, CommentDto commentDto) {
        return this.commentRepository.commentUpdate(commentId, commentDto);
    }

    public void commentDelete(Integer commentId) {
        this.commentRepository.commentDelete(commentId);
    }
}
