package com.example.espractice.member.dto;

import lombok.Getter;

@Getter
public class MemberSaveRequest {

    private String name;

    private String nickname;

    private int age;

    private Long zoneId;

    private String description;

}
