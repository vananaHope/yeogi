package com.vanana.yeogi.base.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.vanana.yeogi.base.entity.QTermsEt;
import com.vanana.yeogi.base.entity.TermsEt;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.vanana.yeogi.base.entity.QTermsEt.termsEt;

@Repository
@RequiredArgsConstructor
public class TermsQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<TermsEt> findAllRecentTerms(){
        QTermsEt subTerms = new QTermsEt("subTerms");

        return queryFactory
                .select(Projections.fields(TermsEt.class,
                            termsEt.termsId,
                            termsEt.title,
                            termsEt.version,
                            termsEt.isMandatory)
                        )
                .from(termsEt)
                .where(
                    termsEt.isUseYn.eq(true),
                    Expressions.list(termsEt.title, termsEt.version).in(
                            JPAExpressions
                                    .select(subTerms.title, subTerms.version.max())
                                    .from(subTerms)
                                    .groupBy(subTerms.title)
                    )
                )
                .fetch();
    }

    public List<TermsEt> findRecentTermsDetail(Long termsId){
        return queryFactory
                .select(Projections.fields(TermsEt.class,
                        termsEt.title,
                        termsEt.content)
                )
                .from(termsEt)
                .where(
                        termsEt.isUseYn.eq(true),
                        termsEt.termsId.eq(termsId)
                        )
                .fetch();
    }
}
