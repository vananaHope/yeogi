package com.vanana.yeogi.base.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vanana.yeogi.base.entity.QTermsEt;
import com.vanana.yeogi.base.entity.TermsEt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vanana.yeogi.base.entity.QTermsEt.termsEt;

@Repository
@RequiredArgsConstructor
public class TermsQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 최신 약관 목록 조회
     */
    public List<TermsEt> findAllRecentTerms(){
        QTermsEt subTerms = new QTermsEt("subTerms");

        return queryFactory
                .select(Projections.fields(TermsEt.class,
                            termsEt.termsId.title,
                            termsEt.termsId.version,
                            termsEt.isMandatory)
                        )
                .from(termsEt)
                .where(
                    termsEt.isUsed.eq(true),
                    Expressions.list(termsEt.termsId.title, termsEt.termsId.version).in(
                            JPAExpressions
                                    .select(subTerms.termsId.title, subTerms.termsId.version.max())
                                    .from(subTerms)
                                    .groupBy(subTerms.termsId.title)
                    )
                )
                .fetch();
    }

    /**
     * 최신 약관 디테일 정보 조회 - 버전 상관 없이 최신 버전만
     * @param title 약관 제목
     */
    public List<TermsEt> findRecentTermsDetail(String title){
        return queryFactory
                .select(Projections.fields(TermsEt.class,
                        termsEt.termsId.title,
                        termsEt.content,
                        termsEt.termsId.version.max())
                )
                .from(termsEt)
                .where(
                        termsEt.isUsed.eq(true),
                        termsEt.termsId.title.eq(title)
                        )
                .fetch();
    }

    //TODO
    //  - 모든 버전 약관 조회 (관리자용)
    //  - 버전별 그룹핑하여 리턴
}
