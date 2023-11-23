package com.example.festival.like.event_like.entity;
import com.example.festival.event.entity.Event;
import com.example.festival.festival.entity.Festival;
import com.example.festival.like.festival_like.entity.Festival_like_ID;
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
@Table(name = "Event_like")
@IdClass(Event_Like_id.class) // 복합 기본 키 클래스를 지정
public class Event_like {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event; //이벤트 글
}