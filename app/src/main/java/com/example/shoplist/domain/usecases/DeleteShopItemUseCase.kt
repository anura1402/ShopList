package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class DeleteShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun deleteItem(shopItem: ShopItem) {
        shopListRepository.deleteItem(shopItem)
    }
}