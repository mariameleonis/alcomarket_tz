package com.alcomarket.demo.event

import com.alcomarket.demo.service.Comparison
import com.alcomarket.demo.service.SortDirection
import java.math.BigDecimal

data class ItemsByPriceRequestEvent(
    val price: BigDecimal,
    val comparison: Comparison,
    val sortDirection: SortDirection
)