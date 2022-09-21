package com.example.espractice.member.dto;


import java.util.List;
import lombok.Getter;

@Getter
public class MemberSaveAllRequest {

    private List<MemberSaveRequest> memberSaveRequestList;
}
