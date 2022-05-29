package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem

interface CartItemDataSource {
    suspend fun getCartItems():List<CartItem> //coroutinescope 실행 강제
    suspend fun addCartItem(cartItem: CartItem)
}