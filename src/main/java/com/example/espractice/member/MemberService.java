package com.example.espractice.member;

import com.example.espractice.member.dto.MemberResponse;
import com.example.espractice.member.dto.MemberSaveAllRequest;
import com.example.espractice.member.dto.MemberSaveRequest;
import com.example.espractice.member.dto.SearchCondition;
import com.example.espractice.member.search.MemberSearchQueryRepository;
import com.example.espractice.member.search.MemberSearchRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberSearchRepository memberSearchRepository;
    private final MemberSearchQueryRepository memberSearchQueryRepository;


    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        Member member = memberRepository.save( Member.from( memberSaveRequest ) );

        System.out.println( member.getZone().getMainZone() );

        MemberDocument memberDocument = MemberDocument.from( member );
        memberSearchRepository.save( memberDocument );
    }

    @Transactional
    public void saveAllMember(MemberSaveAllRequest memberSaveAllRequest) {
        List<Member> memberList =
            memberSaveAllRequest.getMemberSaveRequestList().stream().map(Member::from).collect(Collectors.toList());
        memberRepository.saveAll(memberList);
    }

    @Transactional
    public void saveAllMemberDocuments() {
        List<MemberDocument> memberDocumentList
            = memberRepository.findAll().stream().map(MemberDocument::from).collect(Collectors.toList());
        memberSearchRepository.saveAll(memberDocumentList);
    }


    public List<MemberResponse> findByNickname(String nickname, Pageable pageable) {
        return memberSearchRepository.findByNickname(nickname, pageable)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    public List<MemberResponse> findByAge(int age){
        return memberSearchRepository.findByAge(age)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    public List<MemberResponse> searchByCondition(SearchCondition searchCondition, Pageable pageable) {
        return memberSearchQueryRepository.findByCondition(searchCondition, pageable)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    public List<MemberResponse> findByStartWithNickname(String nickname, Pageable pageable) {
        return memberSearchQueryRepository.findByStartWithNickname(nickname, pageable)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    public List<MemberResponse> findByMatchesDescription(String description, Pageable pageable) {
        return memberSearchQueryRepository.findByMatchesDescription(description, pageable)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    public List<MemberResponse> findByContainsDescription(String description, Pageable pageable) {
        return memberSearchQueryRepository.findByContainsDescription(description, pageable)
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

}