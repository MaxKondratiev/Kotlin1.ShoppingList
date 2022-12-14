package com.example.kotlin1shoppinglist.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin1shoppinglist.data.ShopItemRepositoryImpl
import com.example.kotlin1shoppinglist.domain.*

class MainViewModel: ViewModel() {

    private  val repository = ShopItemRepositoryImpl  //временно так, затычка

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private  val editItemUseCase = EditItemUseCase(repository)
    private  val showSecondScreenUseCase =ShowSecondScreenUseCase(repository)
    
    val shopListLiveDataRe = getShopListUseCase.getShopList()

    fun deleteItem(item: ShopingItem) {
       deleteItemUseCase.deleteItem(item)

    }
    fun changeEnableState( item:ShopingItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editItemUseCase.editItem(newItem)
    }

    fun showSecondScreen (item:ShopingItem) {
              showSecondScreenUseCase.showScreen(item)
        Log.d(
            "TEST3",
            "$item.name")
    }

    
}