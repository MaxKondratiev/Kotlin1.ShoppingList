package com.example.kotlin1shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin1shoppinglist.domain.ShopingItem

class ShopItemDiffCallBack: DiffUtil.ItemCallback<ShopingItem>() {

    override fun areItemsTheSame(oldItem: ShopingItem, newItem: ShopingItem): Boolean {
         return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopingItem, newItem: ShopingItem): Boolean {
        return oldItem == newItem
    }

}