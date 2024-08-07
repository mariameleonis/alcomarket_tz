package com.alcomarket.demo.service

import com.alcomarket.demo.dto.ItemWithAvailabilityDto
import com.alcomarket.demo.event.ItemsByPriceRequestEvent
import com.alcomarket.demo.exception.ItemNotFoundException
import com.alcomarket.demo.mapper.ItemMapper
import com.alcomarket.demo.model.Item
import com.alcomarket.demo.query.ItemQueryBuilder
import com.alcomarket.demo.repository.ItemRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal

enum class Comparison {
    GREATER, LESS
}

enum class SortDirection {
    ASC, DESC
}

@Service
class ItemService(
    private val mongoTemplate: MongoTemplate,
    private val itemRepository: ItemRepository,
    private val itemAvailabilityService: ItemAvailabilityService,
    private val eventPublisher: ApplicationEventPublisher
) {
    private val logger: Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)

    fun checkAvailabilityAndUpdatePrice(itemId: String): ItemWithAvailabilityDto {
        val item = itemRepository.findById(itemId)
            .orElseThrow { ItemNotFoundException(itemId) }

        val priceAsString = item.price.toString()
        var itemAvailability: Boolean

        try {
            val availabilityResponse = itemAvailabilityService.checkAvailability(item.id, priceAsString)
            itemAvailability = availabilityResponse.available

            if (itemAvailability) {
                item.price = availabilityResponse.updatedPrice
                itemRepository.save(item)
            }
        } catch (ex: Exception) {
            logger.error("Error checking availability for item ${item.id}", ex)
            itemAvailability = false
        }

        return ItemMapper.toDto(item, itemAvailability)
    }


    fun getItemsByPrice(price: BigDecimal, comparison: Comparison, sortDirection: SortDirection): List<Item> {
        val query = ItemQueryBuilder.buildQuery(price, comparison, sortDirection)
        eventPublisher.publishEvent(ItemsByPriceRequestEvent(price, comparison, sortDirection))
        return mongoTemplate.find(query, Item::class.java)
    }
}