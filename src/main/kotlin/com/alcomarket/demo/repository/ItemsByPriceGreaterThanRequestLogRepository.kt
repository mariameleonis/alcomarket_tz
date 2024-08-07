package com.alcomarket.demo.repository

import com.alcomarket.demo.model.ItemsByPriceRequestLog
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemsByPriceGreaterThanRequestLogRepository : MongoRepository<ItemsByPriceRequestLog, String>