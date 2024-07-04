package com.example.shoplist.data.mapper

import com.example.shoplist.data.database.ShopItemDBModel
import com.example.shoplist.domain.ShopItem
import javax.inject.Inject

class ShopListMapper @Inject constructor(){
    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDBModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDBModel: ShopItemDBModel) = ShopItem(
        id = shopItemDBModel.id,
        name = shopItemDBModel.name,
        count = shopItemDBModel.count,
        enabled = shopItemDBModel.enabled
    )


    fun mapListDbModelToListEntity(list:List<ShopItemDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}