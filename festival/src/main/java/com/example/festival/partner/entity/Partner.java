package com.example.festival.partner.entity;

import com.example.festival.festival.entity.Festival;
import com.example.festival.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Integer partnerId;

    @Column(name = "title", nullable = false, length = 100)
    private String title; //제목

    @Column(name = "content", nullable = false, columnDefinition="TEXT")
    private String content; //내용

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt; // 작성일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival; //축제 정보

    @Column(columnDefinition="TEXT")
    private String image;  //이미지


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis()); //한국 시간이 안 됨
        //this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
