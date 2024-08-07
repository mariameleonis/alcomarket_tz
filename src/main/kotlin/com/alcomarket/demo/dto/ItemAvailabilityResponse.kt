package com.alcomarket.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ItemAvailabilityResponse(
    val id: String,
    val available: Boolean,
    @JsonProperty("updated_price")
    val updatedPrice: BigDecimal
)