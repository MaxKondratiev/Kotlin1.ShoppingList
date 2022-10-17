package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import android.util.Log
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem
import java.lang.Exception

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var count = 0
    var shopList = listOf<ShopingItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

//    var onItemLongClickListener: onShopItemLongClickListener? = null
    var onShopItemListener: ((ShopingItem) -> Unit)? = null
    var onShopItemFastClickListener: ((ShopingItem)-> Unit)? = null

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("TEST", "onCreateViewHolder ${++count}" )

        val shopItem = shopList[viewType]
         val layout = when (viewType) {
             VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
             VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
             else -> throw Exception("Unknown view type: $viewType")
             
         }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,parent,false
        )

        return   ShopItemViewHolder(view)


    }

    

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        val shopItem = shopList[position]

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

        //onLongClick
        holder.itemView.setOnLongClickListener {
                 // onItemLongClickListener?.onShopItemLongClick(shopItem)
            onShopItemListener?.invoke(shopItem)
            true
        }

        //onClick
        holder.itemView.setOnClickListener {
            onShopItemFastClickListener?.invoke(shopItem)
        }
        




    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
         return if (item.enabled) {
             VIEW_TYPE_ENABLED
        }   else {
             VIEW_TYPE_DISABLED
        }

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 20
    }

    interface onShopItemLongClickListener{
        fun onShopItemLongClick(shopingItem: ShopingItem)
    }


}

