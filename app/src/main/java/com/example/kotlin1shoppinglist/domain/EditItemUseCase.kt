package com.example.kotlin1shoppinglist.domain

class EditItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editItem(item: ShopingItem)  {
     shopItemRepository.editItem(item)
    }
}