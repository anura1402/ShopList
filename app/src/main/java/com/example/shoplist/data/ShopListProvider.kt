package com.example.shoplist.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.shoplist.data.database.ShopListDao
import com.example.shoplist.presentation.ShopListApp
import javax.inject.Inject

class ShopListProvider:ContentProvider() {

    private val component by lazy {
        (context as ShopListApp).component
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.example.shoplist","shop_items", GET_SHOP_ITEMS_QUERY)
        addURI("com.example.shoplist","shop_items/*", GET_SHOP_ITEMS_STRING_QUERY)
    }


    override fun onCreate(): Boolean {
        component.inject(this)
        //создание прошло успешно
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        return when(code){
            GET_SHOP_ITEMS_QUERY -> {
                 shopListDao.getShopListCursor()
            }
            else ->{
                return null
            }
        }
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        const val GET_SHOP_ITEMS_QUERY = 100
        const val GET_SHOP_ITEMS_STRING_QUERY = 101
    }
}