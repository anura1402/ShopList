package com.example.shoplist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shoplist.data.database.AppDatabase
import com.example.shoplist.data.database.ShopListDao
import com.example.shoplist.data.mapper.ShopListMapper
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao:ShopListDao,// = AppDatabase.getInstance(application).shopListDao(),
    private val mapper:ShopListMapper// = ShopListMapper()
) : ShopListRepository {


    override suspend fun addItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun getItem(itemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(itemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDao.getShopList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
    // второй способ
//    override fun getShopList(): LiveData<List<ShopItem>> = shopListDao.getShopList().map {
//        mapper.mapListDbModelToListEntity(it)
//    }

    override suspend fun editItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

}