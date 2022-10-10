package com.example.kotlin1shoppinglist.presentation

import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopingItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enabled, parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        val shopItem = shopList[position]
        val status = if ( shopItem.enabled) {
            "Active"
        }       else {
            "Not Active"
        }
        holder.itemView.setOnClickListener {
            true
        }
        if(shopItem.enabled) {
            holder.tvName.text = "${shopItem.name}  $status"
            holder.tvCount.text = shopItem.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,
            android.R.color.holo_red_dark))
        }  else {
            holder.tvName.text = ""
            holder.tvCount.text = ""
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,
                android.R.color.white))
        }


    }

    override fun getItemCount(): Int {
        return shopList.size
    }
}