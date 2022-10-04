package com.example.kotlin1shoppinglist.domain

class DeleteItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun deleteItem(item: ShopingItem) {
                shopItemRepository.deleteItem(item)
    }
}