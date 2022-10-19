package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnEdititngFinishedListener {

    private  lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter
    private  var shopItemContainer: FragmentContainerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopItemContainer = findViewById(R.id.shop_item_container)

        setupRecylcerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopListLiveDataRe.observe(this) {

//            adapter.shopList = it
            adapter.submitList(it) 
        }
        //  OnClickListener
        val button = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        button.setOnClickListener{
            //check if we are in land mode or not
            if(shopItemContainer !== null) {

                     launchFragment(ShopItemFragment.newInstanceAddItem())
            } else {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()
    }

     override fun onEditingFinished() {
        Toast.makeText(this, "Success" , Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
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
            if(shopItemContainer !== null) {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            } else {
                val intent = ShopItemActivity.newIntentEditItem(this,it.id)
                startActivity(intent)
            }
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
//                    val item = adapter.shopList[viewHolder.adapterPosition]
                    val item = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteItem(item)
                }
            }
        val itemTouchHelper = ItemTouchHelper(swipeHandlerCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerViewShopList)
    }


}