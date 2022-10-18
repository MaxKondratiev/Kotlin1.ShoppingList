package com.example.kotlin1shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    //Errors  handlers
    private val _errorInputName = MutableLiveData<Boolean>()
            val errorInputName: LiveData<Boolean>
                get() {
                   return  _errorInputName
                }
    private  val _errorInputCount = MutableLiveData<Boolean>()
             val  errorInputCount: LiveData<Boolean>
             get() {
                 return _errorInputCount
             }
    //  LiveData
    private val _shopItem = MutableLiveData<ShopingItem>()
            val shopItem: LiveData<ShopingItem>
            get() {
                return _shopItem
            }
    //   LiveData
    private val _isTimeToFinishScreen = MutableLiveData<Unit>()
            val  isTimeToFinishScreen : LiveData<Unit>
                get() {
                    return  _isTimeToFinishScreen
                }


    fun getShopItem(shopingItemId: Int) {
        val item = getShopItemUseCase.getItem(shopingItemId)
        _shopItem.value = item
    }


    fun addShopItem( inputName: String? , inputCount: String?) {
        val name = validateName(inputName)
        val count = validateCount(inputCount)
        if (validateInput(name, count)) {
            val item = ShopingItem(name, count,true)
            addShopItemUseCase.addItem(item)
            _isTimeToFinishScreen.value = Unit
        }
    }

    fun editShopItem(inputName: String? , inputCount: String?) {
        val name = validateName(inputName)
        val count = validateCount(inputCount)
        if (validateInput(name, count)) {
           _shopItem.value?.let {
               val item = it.copy(name,count)
               editItemUseCase.editItem(item )
               _isTimeToFinishScreen.value = Unit
           }
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
            _errorInputName.value = true
            result = false
       }
        if (count <= 0 ) {
            //TODO: Show error
            _errorInputCount.value = true
            result = false
        }
        return  result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }
    public fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

}