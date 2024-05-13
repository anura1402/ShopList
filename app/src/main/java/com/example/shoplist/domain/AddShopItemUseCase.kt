package com.example.shoplist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}