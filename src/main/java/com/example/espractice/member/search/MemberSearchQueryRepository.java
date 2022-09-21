package com.example.espractice.member.search;


import com.example.espractice.member.MemberDocument;
import com.example.espractice.member.dto.SearchCondition;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.data.elasticsearch.core.query.Query;

@Repository
@RequiredArgsConstructor
public class MemberSearchQueryRepository {

    private final ElasticsearchOperations operations;

    public List<MemberDocument> findByCondition(SearchCondition searchCondition, Pageable pageable) {
        CriteriaQuery query = createConditionCriteriaQuery(searchCondition).setPageable(pageable);

        SearchHits<MemberDocument> search = operations.search(query, MemberDocument.class);
        return search.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    private CriteriaQuery createConditionCriteriaQuery(SearchCondition searchCondition) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if (searchCondition == null)
            return query;

        if (searchCondition.getId() != null)
            query.addCriteria(Criteria.where("id").is(searchCondition.getId()));

        if(searchCondition.getAge() > 0)
            query.addCriteria(Criteria.where("age").is(searchCondition.getAge()));

        if(StringUtils.hasText(searchCondition.getName()))
            query.addCriteria(Criteria.where("name").is(searchCondition.getName()));

        if(StringUtils.hasText(searchCondition.getNickname()))
            query.addCriteria(Criteria.where("nickname").is(searchCondition.getNickname()));

        if(searchCondition.getZoneId() != null)
            query.addCriteria(Criteria.where("zone.id").is(searchCondition.getZoneId()));

        if(searchCondition.getStatus() != null)
            query.addCriteria(Criteria.where("status").is(searchCondition.getStatus()));

        return query;
    }

    public List<MemberDocument> findByStartWithNickname(String nickname, Pageable pageable) {
        Criteria criteria = Criteria.where("nickname").startsWith(nickname);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<MemberDocument> search = operations.search(query, MemberDocument.class);
        return search.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    /**
     * 일반적으로 원하는 description이 매칭되는 것들에 맞게 score 계산해서 찾아준다.
     */
    public List<MemberDocument> findByMatchesDescription(String description, Pageable pageable) {
        Criteria criteria = Criteria.where("description").matches(description);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<MemberDocument> search = operations.search(query, MemberDocument.class);
        return search.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    public List<MemberDocument> findByContainsDescription(String description, Pageable pageable) {
        Criteria criteria = Criteria.where("description").contains(description);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<MemberDocument> search = operations.search(query, MemberDocument.class);
        return search.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }




}
