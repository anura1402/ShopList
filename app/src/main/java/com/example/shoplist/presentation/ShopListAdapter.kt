package com.example.shoplist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    /* при использовании ShopListDiffCallback
    var shopList = listOf<ShopItem>()
    set(value) {
        val callback = ShopListDiffCallback(shopList, value)
        val diffResult= DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
    }
    */
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        //val shopItem = shopList[position] при использовании ShopListDiffCallback
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.view.setOnLongClickListener {
            //если не null, то вызовется функция
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else
            VIEW_TYPE_DISABLED
    }


    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 25
    }
}