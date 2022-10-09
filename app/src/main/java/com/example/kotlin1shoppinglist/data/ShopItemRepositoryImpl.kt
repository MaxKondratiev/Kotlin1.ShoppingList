package com.example.kotlin1shoppinglist.data

import com.example.kotlin1shoppinglist.domain.ShopItemRepository
import com.example.kotlin1shoppinglist.domain.ShopingItem
import java.lang.RuntimeException

object ShopItemRepositoryImpl: ShopItemRepository {

    private val shopList = mutableListOf<ShopingItem>()
    private var autoIncrementId = 0     


    override fun addItem(item: ShopingItem) {
        if(item.id == ShopingItem.UNDEFINED_ID)  {
            item.id = autoIncrementId
            autoIncrementId++
        }
        shopList.add(item)
    }

    override fun deleteItem(item: ShopingItem) {
       shopList.remove(item)
    }

    override fun getShopList(): List<ShopingItem> {
      return shopList.toList()
        //.toList - чтобы создать копию Lista
    }

    override fun getItem(itemId: Int): ShopingItem {
        return shopList.find{ it.id == itemId  } ?: throw RuntimeException(
            "Element with id: $itemId not found")
    }

    override fun editItem(item: ShopingItem) {
        val oldElement = getItem(item.id)
        shopList.remove(oldElement)
        shopList.add(item)
        
    }

    
}