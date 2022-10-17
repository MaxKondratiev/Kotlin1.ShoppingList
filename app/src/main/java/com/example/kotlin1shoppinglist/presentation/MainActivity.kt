package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED, ShopListAdapter.MAX_POOL_SIZE)
        recyclerViewShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED, ShopListAdapter.MAX_POOL_SIZE)


        adapter.onShopItemListener =  {
            viewModel.changeEnableState(it)
        }                                                           
        adapter.onShopItemFastClickListener = {
                               viewModel.showSecondScreen(it)
        }

        setupSwipeListener(recyclerViewShopList)
    }


    private fun setupSwipeListener(recyclerViewShopList: RecyclerView) {
        val swipeHandlerCallBack =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    Log.d("TEST4", "DELETED")
                    val item = adapter.shopList[viewHolder.adapterPosition]
                    viewModel.deleteItem(item)
                }
            }
        val itemTouchHelper = ItemTouchHelper(swipeHandlerCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerViewShopList)
    }


}