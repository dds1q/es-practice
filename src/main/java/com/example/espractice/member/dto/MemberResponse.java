package com.example.espractice.member.dto;

import com.example.espractice.member.MemberDocument;
import com.example.espractice.member.Status;
import com.example.espractice.zone.Zone;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {
    private Long id;

    private String name;

    private String nickname;

    private int age;

    private Status status;

    private Zone zone;

    private String description;

    private LocalDateTime createdAt;

    public static MemberResponse from(MemberDocument memberDocument){
        return MemberResponse.builder()
            .id(memberDocument.getId())
            .name(memberDocument.getName())
            .nickname(memberDocument.getNickname())
            .age(memberDocument.getAge())
            .status(memberDocument.getStatus())
            .zone(memberDocument.getZone())
            .description(memberDocument.getDescription())
            .createdAt(memberDocument.getCreatedAt())
            .build();
    }
}