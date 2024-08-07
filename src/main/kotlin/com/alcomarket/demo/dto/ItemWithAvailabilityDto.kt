package com.alcomarket.demo.dto

import java.math.BigDecimal

data class ItemWithAvailabilityDto (
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val price: BigDecimal,
    val availability: Boolean
)