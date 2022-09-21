package com.example.espractice.member;

import com.example.espractice.BaseEntity;
import com.example.espractice.member.dto.MemberSaveRequest;
import com.example.espractice.zone.Zone;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private int age;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private String description;

    public static Member from (MemberSaveRequest memberSaveRequest){
        return Member.builder()
            .name(memberSaveRequest.getName())
            .nickname(memberSaveRequest.getNickname())
            .age(memberSaveRequest.getAge())
            .status(Status.WAIT)
            .zone(Zone.builder().id(memberSaveRequest.getZoneId()).build())
            .description(memberSaveRequest.getDescription())
            .build();
    }
}
