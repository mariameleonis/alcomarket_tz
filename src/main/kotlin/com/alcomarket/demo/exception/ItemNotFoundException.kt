package com.alcomarket.demo.exception

class ItemNotFoundException(id: String) : RuntimeException("Item with ID $id not found")