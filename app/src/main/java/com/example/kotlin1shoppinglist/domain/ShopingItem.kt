package com.example.kotlin1shoppinglist.domain

data class ShopingItem(
    val id: Int,
    val name:String,
    val count: Int,
    val enabled: Boolean
)
