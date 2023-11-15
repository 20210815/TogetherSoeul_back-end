package com.example.festival.partner.dto;

import com.example.festival.user.dto.UserDto;
import com.example.festival.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDto {
    private Integer partnerId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private int count; //댓글, 답글 총 개수
    private String nickname; //작성자 닉네임 전달
    private String address; //작성자 주소
    private Integer festivalId; //축제 아이디
    private String image; //동행자 이미지
}
