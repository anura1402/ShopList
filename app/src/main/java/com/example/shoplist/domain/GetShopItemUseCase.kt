package com.example.shoplist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getItem(itemId:Int): ShopItem{
        return shopListRepository.getItem(itemId)
    }
}