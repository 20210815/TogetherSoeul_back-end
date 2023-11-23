package com.example.festival.idea.entity;


import com.example.festival.festival.entity.Festival;
import com.example.festival.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ideaId; // 제안 글 아이디

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Column(nullable = false, columnDefinition="TEXT")
    private String content; // 내용

    @CreatedDate
    private LocalDateTime createdAt; // 작성일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자

    @Column(columnDefinition="TEXT")
    private String image;  //이미지


}
