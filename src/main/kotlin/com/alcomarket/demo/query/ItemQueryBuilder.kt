package com.alcomarket.demo.query

import com.alcomarket.demo.service.Comparison
import com.alcomarket.demo.service.SortDirection
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.math.BigDecimal

private const val PRICE = "price"

object ItemQueryBuilder {

    fun buildQuery(price: BigDecimal, comparison: Comparison, sortDirection: SortDirection): Query {
        val criteria = createCriteria(price, comparison)
        val sort = createSort(sortDirection)

        return Query(criteria).with(sort)
    }

    private fun createCriteria(price: BigDecimal, comparison: Comparison): Criteria {
        return when (comparison) {
            Comparison.GREATER -> Criteria.where(PRICE).gt(price)
            Comparison.LESS -> Criteria.where(PRICE).lt(price)
        }
    }

    private fun createSort(sortDirection: SortDirection): Sort {
        val direction = if (sortDirection == SortDirection.ASC) Sort.Direction.ASC else Sort.Direction.DESC
        return Sort.by(Sort.Order(direction, PRICE))
    }
}