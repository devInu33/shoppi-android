package com.shoppi.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.shoppi.app.model.CartItem

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cart_item")
    suspend fun load():List<CartItem>

    @Insert(onConflict = REPLACE)
    suspend fun insert(cartItem: CartItem)
}