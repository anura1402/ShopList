package com.example.shoplist.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.presentation.adapters.ShopListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var shopItemContainer: FragmentContainerView? = null

    private val component by lazy {
        (application as ShopListApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            // Обновление данных адаптера, когда изменяется список покупок
            shopListAdapter.shopList = it

        }

        //ADD_MODE
        val button = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        button.setOnClickListener {
            if (!isOnePaneMode()) {
                val fragment = ShopItemFragment.newInstanceAddItem()
                launchFragment(fragment)
            } else {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            }
        }
        thread {
            val cursor = contentResolver.query(
                Uri.parse("content://com.example.shoplist/shop_items"),
                null,
                null,
                null,
                null
            )

            while (cursor?.moveToNext() == true){
                val id  = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name  = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val count  = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                val enabled  = cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) > 0
                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enabled = enabled
                )
                Log.d("MainActivityA", shopItem.toString())
            }
            cursor?.close()
        }

    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack() //удаление верхнего объекта из backstack(стек из поочередно запущенных активити)
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
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
        setupOnLongClickListener()
        setupOnClickListener()
        //удаление элемента свайпом
        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.adapterPosition]
//                viewModel.deleteShopItem(item)
                contentResolver.delete(
                    Uri.parse("content://com.example.shoplist/shop_items"),
                    null,
                    arrayOf(item.id.toString())
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    // EDIT_MODE
    private fun setupOnClickListener() {
        shopListAdapter.onShopItemClickListener = {
            Log.d(
                "ShopListAdapter",
                "MainActivity, shopItem: name: ${it.name} count: ${it.count}  enabled: ${it.enabled}"
            )
            if (!isOnePaneMode()) {
                val fragment = ShopItemFragment.newInstanceEditItem(it.id)
                launchFragment(fragment)
            } else {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            }
        }
    }

    private fun setupOnLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

}