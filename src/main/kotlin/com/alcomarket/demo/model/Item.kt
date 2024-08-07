package com.alcomarket.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal

@Document(collection = "items")
data class Item(
    @Id val id: String,
    val name: String,
    val description: String,
    val icon: String,

    @Field(targetType = FieldType.DECIMAL128)
    var price: BigDecimal
)