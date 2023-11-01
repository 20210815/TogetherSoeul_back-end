package com.example.festival.user.entity;


import javax.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")
public class User {
    //이미지 제외

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "nickname", nullable = false, length = 20, unique = true)
    private String nickName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

//    @Column(name = "created_at", nullable = false)
//    private Date createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = new java.sql.Date(Instant.now().toEpochMilli());
//    }
}