package com.example.shoplist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editItem(shopItem: ShopItem){
        shopListRepository.editItem(shopItem)
    }
}