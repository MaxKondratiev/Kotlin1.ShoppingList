package com.example.kotlin1shoppinglist.domain

class GetShopListUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopList(): List<ShopingItem> {
        return  shopItemRepository.getShopList()
    }
}
