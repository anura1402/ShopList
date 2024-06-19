package com.example.shoplist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}