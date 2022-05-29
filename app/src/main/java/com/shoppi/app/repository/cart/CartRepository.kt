package com.shoppi.app.repository.cart

import android.util.Log
import com.shoppi.app.model.CartItem
import com.shoppi.app.model.CartProduct
import com.shoppi.app.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(private val dataSource: CartItemLocalDataSource, private val ioDispatcher:CoroutineDispatcher=Dispatchers.IO) {
    suspend fun addCartItem(product: Product){
        return withContext(ioDispatcher){
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnail?:"",
                type = product.type ?:"",
                amount = 1
            )
            Log.d("cart", "ADD")
            dataSource.addCartItem(cartItem)
        }
    }
    suspend fun getCartItems():List<CartItem>{
        return withContext(ioDispatcher){
            Log.d("cart", "GET")
            dataSource.getCartItems()
        }
    }
}