package com.example.kotlin1shoppinglist.domain

interface ShopItemRepository {

    fun getShopList(): List<ShopingItem>
    fun getItem(item: ShopingItem)
    fun editItem(item: ShopingItem)
    fun deleteItem(item: ShopingItem)
    fun addItem(item : ShopingItem)

}