package com.alcomarket.demo.service

import com.alcomarket.demo.model.Item
import com.alcomarket.demo.repository.ItemRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ScheduledTasks(
    private val itemRepository: ItemRepository,
    private val restTemplate: RestTemplate
) {
    @Value("\${fetch.items.url}")
    lateinit var url: String

    private val logger: Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)

    @Scheduled(cron = "\${fetch.items.scheduled.job.cron}")
    fun fetchItems() {
        logger.info("Fetching items from URL: $url")

        try {
            val response = restTemplate.getForObject(url, Array<Item>::class.java)
            if (response.isNullOrEmpty()) {
                logger.warn("No items found or received an empty response.")
            } else {
                val itemsList = response.toList()
                logger.info("Fetched ${itemsList.size} items.")
                itemRepository.saveAll(itemsList)
                logger.info("Items saved to database.")
            }
        } catch (e: Exception) {
            logger.error("Error occurred while fetching or saving items", e)
        }
    }
}