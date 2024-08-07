package com.alcomarket.demo.repository

import com.alcomarket.demo.model.Item
import org.springframework.data.mongodb.repository.MongoRepository
import java.math.BigDecimal

interface ItemRepository : MongoRepository<Item, String> {
    fun findByPriceGreaterThanOrderByPriceAsc(price: BigDecimal): List<Item>
}