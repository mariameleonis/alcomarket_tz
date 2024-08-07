package com.alcomarket.demo.service

import com.alcomarket.demo.dto.ItemAvailabilityResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ItemAvailabilityService(private val restTemplate: RestTemplate) {

    @Value("\${item.availability.url}")
    lateinit var url: String

    fun checkAvailability(itemId: String, price: String): ItemAvailabilityResponse {
        val url = "$url?id=$itemId&price=$price"
        return restTemplate.getForObject(url, ItemAvailabilityResponse::class.java)
            ?: throw RuntimeException("Failed to get availability for item $itemId")
    }
}