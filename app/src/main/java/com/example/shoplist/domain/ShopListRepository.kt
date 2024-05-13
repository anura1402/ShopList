package com.example.shoplist.domain

interface ShopListRepository {

    fun addItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun getItem(itemId:Int): ShopItem

    fun getShopList(): List<ShopItem>

    fun editItem(shopItem: ShopItem)

}