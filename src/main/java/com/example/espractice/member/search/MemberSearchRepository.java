package com.example.espractice.member.search;

import com.example.espractice.member.MemberDocument;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSearchRepository extends ElasticsearchRepository<MemberDocument,Long> {

    List<MemberDocument> findByAge(int age);

    List<MemberDocument> findByNickname(String nickname, Pageable pageable);
}
