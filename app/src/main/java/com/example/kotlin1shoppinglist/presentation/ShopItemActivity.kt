package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

    private  lateinit var viewModel: ShopItemViewModel

    private lateinit var tVName: TextInputLayout
    private lateinit var tVCount: TextInputLayout
    private lateinit var  editTextName: EditText
    private lateinit var  editTextCount: EditText
    private  lateinit var buttonSave: Button

    private var screenMode = UNKNOWN_MODE
    private  var shopItemId = ShopingItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()

        
        
        
    }
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
             if(!intent.hasExtra(EXTRA_ITEM_ID)) {
                 throw RuntimeException(" there is no item ID ")
             }
            shopItemId = intent.getIntExtra(EXTRA_ITEM_ID,ShopingItem.UNDEFINED_ID)
        }
    }




    private fun initViews() {
        tVName = findViewById(R.id.tv_name)
        tVCount = findViewById(R.id.tv_count)
        editTextName = findViewById(R.id.et_name)
        editTextCount = findViewById(R.id.et_count)
        buttonSave = findViewById(R.id.save_button)
    }



    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ITEM_ID = "Item_id"

        private const val EDIT_MODE = "mode_edit"
        private const val ADD_MODE = "mode_add"
        private const val UNKNOWN_MODE = ""


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, ADD_MODE )
            return  intent
        }
        fun newIntentEditItem(context: Context, itemId:Int) :Intent {
            val intent = Intent(context,ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, EDIT_MODE)
            intent.putExtra(EXTRA_ITEM_ID, itemId)
            return  intent
        }

    }
}