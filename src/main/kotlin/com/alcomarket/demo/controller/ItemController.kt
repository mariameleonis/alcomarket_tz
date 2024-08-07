package com.alcomarket.demo.controller

import com.alcomarket.demo.dto.ItemWithAvailabilityDto
import com.alcomarket.demo.model.Item
import com.alcomarket.demo.service.Comparison
import com.alcomarket.demo.service.ItemService
import com.alcomarket.demo.service.SortDirection
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("api/v1/items")
class ItemController(private val itemService: ItemService) {

    @GetMapping("/{id}")
    fun getItemWithAvailability(@PathVariable id: String): ResponseEntity<ItemWithAvailabilityDto> {
        val updatedItem = itemService.checkAvailabilityAndUpdatePrice(id)
        return ResponseEntity.ok(updatedItem)
    }

    @GetMapping("/price")
    fun getItemsByPrice(
        @RequestParam price: BigDecimal,
        @RequestParam(required = false, defaultValue = "GREATER") comparison: Comparison,
        @RequestParam(required = false, defaultValue = "ASC") sortDirection: SortDirection
    ): List<Item> {
        require(price >= BigDecimal.ZERO) { "Price must be non-negative" }

        return itemService.getItemsByPrice(price, comparison, sortDirection)
    }
}