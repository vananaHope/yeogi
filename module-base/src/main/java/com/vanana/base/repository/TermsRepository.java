package com.vanana.base.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class TermsRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
