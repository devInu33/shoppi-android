package com.shoppi.app.repository.cart

import com.shoppi.app.AssetLoader
import com.shoppi.app.database.AppDatabase
import com.shoppi.app.database.CartItemDao
import com.shoppi.app.model.CartItem

class CartItemLocalDataSource(private val dao:CartItemDao):CartItemDataSource { //로컬 DB와 통(장바구니)
    override suspend fun getCartItems(): List<CartItem> {
        return dao.load()
    }

    override suspend fun addCartItem(cartItem: CartItem) {
        return dao.insert(cartItem)
    }

}