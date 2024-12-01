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
    override fun findWithSubsByExampleId(id: Long): List<Example> =
            jpaQueryFactory
                    .selectFrom(qExample)
                    .leftJoin(qExample.exampleSubs).fetchJoin()
                    .where(qExample.id.eq(id))
                    .fetch()


    override fun findWithSubsByExampleIdWithPaging(pageable: Pageable): Page<Example> =
            jpaQueryFactory
                    .selectFrom(qExample)
                    .leftJoin(qExample.exampleSubs)
                    .orderBy(qExample.createdAt.desc())
                    .fetchJoin()
                    .let {
                        val paginatedQuery = applyPagination(pageable, it)
                        val content = paginatedQuery.fetch()
                        val total = getCount(it)

                        PageImpl(content, pageable, total)
                    }
}