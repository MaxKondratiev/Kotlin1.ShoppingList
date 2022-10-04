package com.example.kotlin1shoppinglist.domain

class GetItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getItem(item: ShopingItem) {
              shopItemRepository.getItem(item)
    }

}