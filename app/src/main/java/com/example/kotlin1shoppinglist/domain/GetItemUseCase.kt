package com.example.kotlin1shoppinglist.domain

class GetItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getItem(itemId: Int) : ShopingItem{
             return shopItemRepository.getItem(itemId)
    }

}