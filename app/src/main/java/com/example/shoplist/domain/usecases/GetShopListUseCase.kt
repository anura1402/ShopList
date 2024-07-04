package com.example.shoplist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}