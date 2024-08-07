package com.alcomarket.demo.mapper

import com.alcomarket.demo.dto.ItemWithAvailabilityDto
import com.alcomarket.demo.model.Item

class ItemMapper {
    companion object {
        fun toDto(item: Item, availability: Boolean): ItemWithAvailabilityDto =
            ItemWithAvailabilityDto(id = item.id, name = item.name, description = item.description,
                icon = item.icon, price = item.price, availability = availability)
    }
}