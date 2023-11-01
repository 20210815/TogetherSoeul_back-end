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
    private Long boardId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private String nickname; //작성자 닉네임 전달
}
