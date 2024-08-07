package com.alcomarket.demo.model

import com.alcomarket.demo.service.Comparison
import com.alcomarket.demo.service.SortDirection
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Document("items_by_price_request_log")
data class ItemsByPriceRequestLog (
    @Id
    val id: String = UUID.randomUUID().toString(),
    @Field(targetType = FieldType.DECIMAL128)
    val price: BigDecimal,
    val comparison: Comparison,
    val sortDirection: SortDirection,
    val timestamp: Instant = Instant.now()
)