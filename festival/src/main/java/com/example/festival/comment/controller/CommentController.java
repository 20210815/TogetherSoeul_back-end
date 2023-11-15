package com.example.festival.comment.controller;

import com.example.festival.comment.dto.CommentDto;
import com.example.festival.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(@Autowired CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{partnerId}")
    public String commentCreate(@PathVariable("partnerId") Integer partnerId, @RequestBody CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.commentService.commentCreate(authentication.getName(), partnerId, commentDto);
        return "댓글 작성 완료";
    }

    @GetMapping("/{partnerId}") // partnerId에 해당하는 댓글 불러오기
    public List<CommentDto> commentReadAllPartner(@PathVariable("partnerId")Integer partnerId) {
        return this.commentService.commentReadAllByPartner(partnerId);
    }

    @PatchMapping("/{commentId}")
    public String commentUpdate(@PathVariable("commentId")Integer commentId, @RequestBody CommentDto commentDto) {
        return this.commentService.commentUpdate(commentId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void commentDelete(@PathVariable("commentId")Integer commentId) {
        this.commentService.commentDelete(commentId);
    }

}
