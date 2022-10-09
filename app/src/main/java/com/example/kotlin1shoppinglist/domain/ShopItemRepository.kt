package com.example.kotlin1shoppinglist.domain

interface ShopItemRepository {

    fun getShopList(): List<ShopingItem>
    fun getItem(itemId: Int) : ShopingItem
    fun editItem(item: ShopingItem)
    fun deleteItem(item: ShopingItem)
    fun addItem(item : ShopingItem)

}