package com.example.festival.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private Integer replyId;
    private String content; //내용
    private String nickname; //작성자
    private String address; //작성자 주소
    private Integer commentId; //댓글
    private Timestamp createdAt;
}
