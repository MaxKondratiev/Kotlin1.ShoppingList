package com.example.kotlin1shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin1shoppinglist.data.ShopItemRepositoryImpl
import com.example.kotlin1shoppinglist.domain.*

class MainViewModel: ViewModel() {

    private  val repository = ShopItemRepositoryImpl  //временно так, затычка

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private  val editItemUseCase = EditItemUseCase(repository)

    val shopListLiveData = MutableLiveData<List<ShopingItem>>()

    fun getShopList() {
        val shoplist = getShopListUseCase.getShopList()
        shopListLiveData.value = shoplist

    }
                            
    fun deleteItem(item: ShopingItem) {
       deleteItemUseCase.deleteItem(item)
            getShopList() 
    }
    fun changeEnableState( item:ShopingItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editItemUseCase.editItem(item)
        getShopList()
                    
    }
    
}