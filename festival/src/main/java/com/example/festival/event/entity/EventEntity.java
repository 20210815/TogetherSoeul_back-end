package com.example.festival.event.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(columnDefinition="TEXT")
    private String image;

    @Column(nullable = false, length = 500)
    private String location;

    @Column(columnDefinition="TEXT")
    private String rule;

    @Column(columnDefinition="TEXT")
    private String register;

    private Integer ing;

    @Column(nullable = false)
    private String startDay;

    @Column(nullable = false)
    private String endDay;

    @Column(nullable = false)
    private String resultDay;

}
