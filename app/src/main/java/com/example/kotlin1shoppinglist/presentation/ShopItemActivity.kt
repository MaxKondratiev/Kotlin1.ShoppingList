package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tVName: TextInputLayout
//    private lateinit var tVCount: TextInputLayout
//    private lateinit var editTextName: EditText
//    private lateinit var editTextCount: EditText
//    private lateinit var buttonSave: Button
//
    private var screenMode = UNKNOWN_MODE
    private var shopItemId = ShopingItem.UNDEFINED_ID
//
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_shop_item)
    parseIntent()

    //вызов фрагмента
    //c проверкой на первый запуск
    if(savedInstanceState == null) {
        val ourfragment = when (screenMode) {
            EDIT_MODE -> ShopItemFragment.newInstanceEditItem(shopItemId)
            ADD_MODE -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("unknown screen mode")
        }
        supportFragmentManager.beginTransaction()
            //.replace - заменяет  // add добавляет
            .replace(R.id.shop_item_container, ourfragment)
            .commit()
    }

}
//
//
//    private fun observersViewModel() {
//        viewModel.errorInputCount.observe(this) {
//
//            val message = if (it) {
//           getString(R.string.error_input)
//            } else {
//                null
//            }
//            tVCount.error = message.toString()
//        }
//        viewModel.errorInputName.observe(this) {
//
//            val message = if (it) {
//                getString(R.string.error_input)
//            } else {
//                null
//            }
//            tVName.error = message.toString()
//        }
//        viewModel.isTimeToFinishScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            editTextName.setText(it.name)
//            editTextCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener {
//            viewModel.editShopItem(editTextName.text?.toString(), editTextCount.text?.toString())
//        }
//    }
//
//    private fun launchAddMode() {
//
//        buttonSave.setOnClickListener {
//            viewModel.addShopItem(editTextName.text?.toString(), editTextCount.text?.toString())
//        }
//    }
//
//
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException(" there is no Screen mode")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)

        if (mode != EDIT_MODE && mode != ADD_MODE) {
            throw RuntimeException(" Unknown mode")
        }
        screenMode = mode
        if (screenMode == EDIT_MODE) {
            if (!intent.hasExtra(EXTRA_ITEM_ID)) {
                throw RuntimeException(" there is no item ID ")
            }
            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopingItem.UNDEFINED_ID)
        }
    }
//
//
//    private fun initViews() {
//        tVName = findViewById(R.id.til_name)
//        tVCount = findViewById(R.id.til_count)
//        editTextName = findViewById(R.id.et_name)
//        editTextCount = findViewById(R.id.et_count)
//        buttonSave = findViewById(R.id.save_button)
//    }
//
//    private fun addChangeTextListeners() {
//        editTextName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//        editTextCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
//
//
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ITEM_ID = "Item_id"

        private const val EDIT_MODE = "mode_edit"
        private const val ADD_MODE = "mode_add"
        private const val UNKNOWN_MODE = ""


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, ADD_MODE)
            return intent
        }

        fun newIntentEditItem(context: Context, itemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, EDIT_MODE)
            intent.putExtra(EXTRA_ITEM_ID, itemId)
            return intent
        }

    }
}