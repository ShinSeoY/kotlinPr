package com.my.kotlinPr.repository.impl

import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.entity.QExample
import com.my.kotlinPr.entity.QExampleSub
import com.my.kotlinPr.repository.common.QuerydslRepositorySupport
import com.my.kotlinPr.repository.custom.ExampleCustomRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


@Repository
class ExampleCustomRepositoryImpl(
        jpaQueryFactory: JPAQueryFactory
) : ExampleCustomRepository, QuerydslRepositorySupport(jpaQueryFactory) {

    val qExample = QExample.example
    val qExampleSub = QExampleSub.exampleSub
    override fun findWithSubsByExampleId(id: Long): List<Example> {
        return jpaQueryFactory
                .selectFrom(qExample)
                .leftJoin(qExample.exampleSubs).fetchJoin()
                .where(qExample.id.eq(id))
                .fetch()
    }

    override fun findWithSubsByExampleIdWithPaging(pageable: Pageable): Page<Example> {
        val query = jpaQueryFactory
                .selectFrom(qExample)
                .leftJoin(qExample.exampleSubs).fetchJoin()

        val paginatedQuery = applyPagination(pageable, query)
        val content = paginatedQuery.fetch()
        val total = getCount(query)

        return PageImpl(content, pageable, total)
    }
}