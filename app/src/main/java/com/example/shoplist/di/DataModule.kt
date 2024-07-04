package com.example.shoplist.di

import android.app.Application
import com.example.shoplist.data.database.AppDatabase
import com.example.shoplist.data.database.ShopListDao
import com.example.shoplist.data.repository.ShopListRepositoryImpl
import com.example.shoplist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}