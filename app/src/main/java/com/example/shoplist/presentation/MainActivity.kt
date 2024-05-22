package com.example.shoplist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            // Обновление данных адаптера, когда изменяется список покупок
            shopListAdapter.shopList = it
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            // Установка адаптера для RecyclerView
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
           recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        shopListAdapter.onShopItemLongClickListener = object : ShopListAdapter.OnShopItemLongClickListener{
            override fun onShopItemLongClick(shopItem: ShopItem) {
                viewModel.changeEnableState(shopItem)
            }
        }
    }
}