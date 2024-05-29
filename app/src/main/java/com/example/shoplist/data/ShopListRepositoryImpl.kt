package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import kotlin.random.Random

//object - singletone для того, чтобы работать с одним и тем же экземпляром репозитория
object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1,o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrementId = 0

    init{
        for (i in 0 until 10){
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addItem(item)
        }
    }
    override fun addItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getItem(itemId: Int): ShopItem {
        return shopList.find{ it.id == itemId} ?: throw RuntimeException("Element with id: $itemId is not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun editItem(shopItem: ShopItem) {
        val oldElement = getItem(shopItem.id)
        shopList.remove(oldElement)
        addItem(shopItem)
    }
    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}