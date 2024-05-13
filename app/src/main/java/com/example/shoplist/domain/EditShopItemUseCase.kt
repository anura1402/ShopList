package com.example.shoplist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editItem(shopItem: ShopItem){
        shopListRepository.editItem(shopItem)
    }
}