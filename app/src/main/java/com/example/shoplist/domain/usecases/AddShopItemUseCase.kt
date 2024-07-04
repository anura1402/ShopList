package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}