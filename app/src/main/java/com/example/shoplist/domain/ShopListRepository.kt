package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun getItem(itemId:Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

    fun editItem(shopItem: ShopItem)

}