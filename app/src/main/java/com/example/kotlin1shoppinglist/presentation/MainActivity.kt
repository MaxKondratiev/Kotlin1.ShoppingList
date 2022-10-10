package com.example.kotlin1shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem

class MainActivity : AppCompatActivity() {

    private  lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecylcerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopListLiveDataRe.observe(this) {

            adapter.shopList = it
        }

    }

    private fun setupRecylcerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        recyclerViewShopList.adapter = adapter
    }

}