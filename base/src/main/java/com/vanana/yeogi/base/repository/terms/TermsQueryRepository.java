package com.vanana.yeogi.base.repository.terms;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vanana.yeogi.base.entity.terms.QTermsEt;
import com.vanana.yeogi.base.entity.terms.TermsEt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vanana.yeogi.base.entity.terms.QTermsEt.termsEt;

@Repository
@RequiredArgsConstructor
public class TermsQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * GUEST 용 최신 약관 목록 조회
     */
    public List<TermsEt> findAllRecentTerms(){
        QTermsEt subTerms = new QTermsEt("subTerms");

        return queryFactory
                .selectFrom(termsEt)
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
     * GUEST 용 최신 약관 디테일 정보 조회 - 버전 상관 없이 최신 버전만
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

    /**
     * 관리자 화면용 약관 목록 조회
     * - 약관 title별 최신 버전 1건만 반환
     * - 사용 여부 상관 없이
     */
    public List<TermsEt> findAllVersionTerms(){
        QTermsEt sub = new QTermsEt("sub");

        return queryFactory
                .selectFrom(termsEt)
                .where(
                        termsEt.termsId.version.eq(
                                JPAExpressions
                                        .select(sub.termsId.version.max())
                                        .from(sub)
                                        .where(sub.termsId.title.eq(termsEt.termsId.title))
                        )
                )
                .fetch();
    }

}
