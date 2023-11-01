package com.example.festival.comment.dto;

import com.example.festival.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String nickname; //댓글 작성자
    private Long partnerId; //게시글
    private String content; //내용
    private Timestamp createdAt;
}
