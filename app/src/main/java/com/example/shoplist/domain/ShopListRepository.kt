package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addItem(shopItem: ShopItem)

    suspend fun deleteItem(shopItem: ShopItem)

    suspend fun getItem(itemId:Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun editItem(shopItem: ShopItem)

}