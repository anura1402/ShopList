package com.example.shoplist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopListRepositoryImpl
import com.example.shoplist.domain.AddShopItemUseCase
import com.example.shoplist.domain.EditShopItemUseCase
import com.example.shoplist.domain.GetShopItemUseCase
import com.example.shoplist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //Mutable - для работы во ViewModel, а LiveData для Activity,
    //но чтобы нельзя было из Activity менять значения этой переменной
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _finishScreen = MutableLiveData<Unit>()
    val finishScreen: LiveData<Unit>
        get() = _finishScreen

    private val scope = CoroutineScope(Dispatchers.IO)

    fun getShopItem(shopItemId: Int) {
        scope.launch {
            val item = getShopItemUseCase.getItem(shopItemId)
            _shopItem.value = item
        }

    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        scope.launch {
            if (fieldsValid) {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addItem(shopItem)
                finishWork()
            }
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        scope.launch {
            if (fieldsValid) {
                val newShopItem = _shopItem.value?.let {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editItem(item)
                    finishWork()
                }
            }
        }

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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }

    public fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    public fun finishWork(){
        _finishScreen.value = Unit
    }
    companion object {
        const val UNDEFINED_COUNT = -1
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}