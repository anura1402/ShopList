package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun editItem(shopItem: ShopItem){
        shopListRepository.editItem(shopItem)
    }
}