package com.example.kotlin1shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.kotlin1shoppinglist.data.ShopItemRepositoryImpl
import com.example.kotlin1shoppinglist.domain.AddItemUseCase
import com.example.kotlin1shoppinglist.domain.EditItemUseCase
import com.example.kotlin1shoppinglist.domain.GetItemUseCase
import com.example.kotlin1shoppinglist.domain.ShopingItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {

    private  val repository = ShopItemRepositoryImpl

    private  val getShopItemUseCase = GetItemUseCase(repository)
    private val addShopItemUseCase = AddItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)


    fun getShopItem(shopingItemId: Int) {
        val item = getShopItemUseCase.getItem(shopingItemId)
        
    }

    fun addShopItem( inputName: String? , inputCount: String?) {
        val name = validateName(inputName)
        val count = validateCount(inputCount)
        if (validateInput(name, count)) {
            val item = ShopingItem(name, count,true)
            addShopItemUseCase.addItem(item)
        }
    }

    fun editShopItem(inputName: String? , inputCount: String?) {
        val name = validateName(inputName)
        val count = validateCount(inputCount)
        if (validateInput(name, count)) {
            val item = ShopingItem(name, count,true)
            editItemUseCase.editItem(item )
        }


    }

    private fun validateName(name: String?): String{
        return name?.trim()  ?: ""
    }

    private fun validateCount(count: String?) : Int {
        return try {
            count?.trim()?.toInt() ?: 0
        }   catch (e: Exception) {
                  0
        }
    }

    private  fun validateInput( name:String, count: Int) : Boolean {
        var result = true
        if (name.isBlank()) {
            //TODO: Show error
            result =   false
       }
        if (count <= 0 ) {
            //TODO: Show error
            result =   false
        }
        return  result
    }

}