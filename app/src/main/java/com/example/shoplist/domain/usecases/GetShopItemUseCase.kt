package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun getItem(itemId:Int): ShopItem {
        return shopListRepository.getItem(itemId)
    }
}