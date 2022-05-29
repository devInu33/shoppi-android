package com.shoppi.app.ui.productdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Product
import com.shoppi.app.repository.cart.CartRepository
import com.shoppi.app.repository.productdetail.ProductDetailRepository
import kotlinx.coroutines.launch
import com.shoppi.app.ui.common.Event
class ProductDetailViewModel(private val productDetailRepository: ProductDetailRepository, private val cartRepository: CartRepository):ViewModel() {
    private val _product=MutableLiveData<Product>()
    val product:LiveData<Product> = _product
    private val _addCartEvent = MutableLiveData<Event<Unit>>()
    val addCartEvent:LiveData<Event<Unit>> = _addCartEvent

    fun loadProductDetail(productId:String){
        viewModelScope.launch {
            productDetailRepository.getProductDetail(productId).let{
                _product.value = it
                Log.d("product",it.toString())
            }
        }
    }
    fun addCart(product:Product){
        viewModelScope.launch {
            cartRepository.addCartItem(product)
            _addCartEvent.value = Event(Unit)
        }
    }
}