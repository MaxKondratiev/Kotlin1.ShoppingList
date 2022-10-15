package com.example.kotlin1shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem

class ShopListAdapter : ListAdapter<ShopingItem, ShopItemViewHolder>(ShopItemDiffCallBack()) {

//    var shopList = listOf<ShopingItem>()                 
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }      Это и была реализация ListAdaptera

//    var onItemLongClickListener: onShopItemLongClickListener? = null
    var onShopItemListener: ((ShopingItem) -> Unit)? = null
    var onShopItemFastClickListener: ((ShopingItem)-> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {


//        val shopItem = shopList[viewType]
       //  val shopItem = getItemViewType(viewType)
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

        val shopItem = getItem(position) // метод из ListAdapter

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
        val item = getItem(position) // метод из ListAdapter
         return if (item.enabled) {
             VIEW_TYPE_ENABLED
        }   else {
             VIEW_TYPE_DISABLED
        }

    }

     //  Этот метод нам не нужен , он есть в ListAdapter
//    override fun getItemCount(): Int {
//        return shopList.size
//    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 20
    }




}

