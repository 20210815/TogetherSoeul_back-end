package com.example.festival.comment.dto;

import com.example.festival.reply.dto.ReplyDto;
import com.example.festival.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String nickname; //댓글 작성자
    private String address; //작성자 주소
    private String userimage; //작성자 프사
    private Integer partnerId; //게시글
    private String content; //내용
    private Timestamp createdAt; //작성일
    private List<ReplyDto> replyDtos; //댓글에 달린 답글
}
