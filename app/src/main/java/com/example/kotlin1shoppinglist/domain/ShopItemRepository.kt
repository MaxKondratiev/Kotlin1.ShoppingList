package com.example.kotlin1shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {

    fun getShopList(): LiveData<List<ShopingItem>>
    fun getItem(itemId: Int) : ShopingItem
    fun editItem(item: ShopingItem)
    fun deleteItem(item: ShopingItem)
    fun addItem(item : ShopingItem)
    fun showScreen(item: ShopingItem): ShopingItem


}