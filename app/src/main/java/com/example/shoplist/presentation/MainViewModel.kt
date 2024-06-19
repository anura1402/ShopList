package com.example.shoplist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplist.data.ShopListRepositoryImpl
import com.example.shoplist.domain.DeleteShopItemUseCase
import com.example.shoplist.domain.EditShopItemUseCase
import com.example.shoplist.domain.GetShopListUseCase
import com.example.shoplist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()



    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch{
            deleteShopItemUseCase.deleteItem(shopItem)
        }


    }
    fun changeEnableState(shopItem: ShopItem){
        viewModelScope.launch{
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editItem(newItem)
        }
    }

}