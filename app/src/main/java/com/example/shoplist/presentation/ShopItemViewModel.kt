package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopListRepositoryImpl
import com.example.shoplist.domain.AddShopItemUseCase
import com.example.shoplist.domain.EditShopItemUseCase
import com.example.shoplist.domain.GetShopItemUseCase
import com.example.shoplist.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)


    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid){
            val shopItem = ShopItem(name,count,true)
            addShopItemUseCase.addItem(shopItem)
        }

    }

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editItem(newItem)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return when (val count = inputCount?.trim()?.toIntOrNull()) {
            null -> UNDEFINED_COUNT
            else -> count
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            //TODO: show error input name
            result = false
        }
        if (count <= 0) {
            //TODO: show error input count
            result = false
        }
        return result
    }

    companion object {
        const val UNDEFINED_COUNT = -1
    }
}