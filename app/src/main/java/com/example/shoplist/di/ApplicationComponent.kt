package com.example.shoplist.di

import android.app.Application
import com.example.shoplist.presentation.MainActivity
import com.example.shoplist.presentation.ShopItemActivity
import com.example.shoplist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: ShopItemActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}