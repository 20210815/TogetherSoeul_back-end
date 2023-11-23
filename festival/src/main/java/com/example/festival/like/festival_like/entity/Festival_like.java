package com.example.festival.like.festival_like.entity;

import com.example.festival.festival.entity.Festival;
import com.example.festival.partner.entity.Partner;
import com.example.festival.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Festival_like")
@IdClass(Festival_like_ID.class) // 복합 기본 키 클래스를 지정
public class Festival_like {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival; //축제 글
}