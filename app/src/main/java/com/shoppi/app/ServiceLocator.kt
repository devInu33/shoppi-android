package com.shoppi.app

import android.content.Context
import androidx.room.Room
import com.shoppi.app.database.AppDatabase
import com.shoppi.app.network.APIClient
import com.shoppi.app.repository.cart.CartItemLocalDataSource
import com.shoppi.app.repository.cart.CartRepository

object ServiceLocator { //인스턴스 제공, 캐싱
    private var apiClient: APIClient? = null
    private var database: AppDatabase? = null
    private var cartRepository:CartRepository? = null
    fun provideApiClient(): APIClient {
        return apiClient ?: kotlin.run {
            APIClient.Create().also {
                apiClient = it
            }
        }
    }

    private fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "shoppi-local")
                .build().also {
                database = it
            }
        }
    }
    fun provideCartRepositroy(context:Context):CartRepository{
        return cartRepository?: kotlin.run{
            val dao= provideDatabase(context.applicationContext).cartItemDao()
            CartRepository(CartItemLocalDataSource(dao)).also {
                cartRepository = it
            }
        }
    }
}