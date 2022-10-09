package com.example.kotlin1shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopList(): LiveData<List<ShopingItem>>{
        return  shopItemRepository.getShopList()
    }
}
