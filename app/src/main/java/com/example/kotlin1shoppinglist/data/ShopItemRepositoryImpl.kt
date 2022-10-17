package com.example.kotlin1shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin1shoppinglist.domain.ShopItemRepository
import com.example.kotlin1shoppinglist.domain.ShopingItem
import java.lang.RuntimeException

object ShopItemRepositoryImpl: ShopItemRepository {

    private val shopList = sortedSetOf<ShopingItem>({
        o1,o2 -> o1.id.compareTo(o2.id)
    })
    private val shopLiveData = MutableLiveData<List<ShopingItem>>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopingItem("Name $i", i , true)
            addItem(item)
        }
    }


    override fun addItem(item: ShopingItem) {
        if(item.id == ShopingItem.UNDEFINED_ID)  {
            item.id = autoIncrementId
            autoIncrementId++
        }

        shopList.add(item)
        updateLiveData()
    }

    override fun deleteItem(item: ShopingItem) {
       shopList.remove(item)
        updateLiveData()
    }

    override fun getShopList(): LiveData<List<ShopingItem>> {
      return shopLiveData
      
    }

    override fun getItem(itemId: Int): ShopingItem {
        return shopList.find{ it.id == itemId  } ?: throw RuntimeException(
            "Element with id: $itemId not found")
    }
    override fun editItem(item: ShopingItem) {
        val oldElement = getItem(item.id)
        shopList.remove(oldElement)
        shopList.add(item)
        updateLiveData()
      
    }

    private  fun updateLiveData() {
        shopLiveData.value = shopList.toList()
    }

    
}