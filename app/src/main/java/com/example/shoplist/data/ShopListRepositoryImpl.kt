package com.example.shoplist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {
    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()


    override fun addItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override fun getItem(itemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(itemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

        override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }
    }
    // второй способ
//    override fun getShopList(): LiveData<List<ShopItem>> = shopListDao.getShopList().map {
//        mapper.mapListDbModelToListEntity(it)
//    }

    override fun editItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

}