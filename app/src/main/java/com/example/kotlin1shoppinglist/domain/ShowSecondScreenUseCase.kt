package com.example.kotlin1shoppinglist.domain

class ShowSecondScreenUseCase (private val shopItemRepository: ShopItemRepository)  {

        fun showScreen(item: ShopingItem): ShopingItem {
             return shopItemRepository.showScreen(item)
        }
    }