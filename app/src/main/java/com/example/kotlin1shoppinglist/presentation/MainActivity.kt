package com.example.kotlin1shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem

class MainActivity : AppCompatActivity() {

    private  lateinit var viewModel: MainViewModel
    private   lateinit var  llshopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llshopList = findViewById(R.id.ll_shopList)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        
        viewModel.shopListLiveDataRe.observe(this) {

            showDataList(it)

        }

    }
    private fun showDataList(list: List<ShopingItem>) {
        llshopList.removeAllViews()
        for (item in list ) {

            val layoutId = if(item.enabled) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId,llshopList,false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = item.name
            tvCount.text = item.count.toString()
            llshopList.addView(view)
            view.setOnLongClickListener {
                viewModel.changeEnableState(item)
                true
            }
            
        }

    }
}