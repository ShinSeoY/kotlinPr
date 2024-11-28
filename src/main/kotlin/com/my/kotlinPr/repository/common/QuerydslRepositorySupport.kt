package com.my.kotlinPr.repository.common

import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable

abstract class QuerydslRepositorySupport(
        protected val jpaQueryFactory: JPAQueryFactory
) {
    protected fun <T> applyPagination(pageable: Pageable, query: JPAQuery<T>): JPAQuery<T> {
        return query
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
    }

    protected fun getCount(query: JPAQuery<*>): Long {
        return query.fetchCount()
    }
}