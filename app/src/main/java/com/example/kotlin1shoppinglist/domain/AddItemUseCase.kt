package com.example.kotlin1shoppinglist.domain

class AddItemUseCase (private val shopItemRepository: ShopItemRepository) {

    fun addItem(item : ShopingItem){
                shopItemRepository.addItem(item)
    }
}
