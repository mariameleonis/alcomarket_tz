package com.alcomarket.demo.event

import com.alcomarket.demo.model.ItemsByPriceRequestLog
import com.alcomarket.demo.repository.ItemsByPriceGreaterThanRequestLogRepository
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ItemsByPriceRequestEventListener (
    private val requestLogRepository: ItemsByPriceGreaterThanRequestLogRepository
) {
    private val logger = LoggerFactory.getLogger(ItemsByPriceRequestEventListener::class.java)

    @EventListener
    fun handlePriceRequestEvent(event: ItemsByPriceRequestEvent) {
        val requestLog = ItemsByPriceRequestLog(price = event.price, comparison = event.comparison, sortDirection = event.sortDirection)

        try {
            requestLogRepository.save(requestLog)
            logger.info("Successfully saved request log for price: ${event.price}")
        } catch (ex: Exception) {
            logger.error("Failed to save request log for price: ${event.price}", ex)
        }
    }
}