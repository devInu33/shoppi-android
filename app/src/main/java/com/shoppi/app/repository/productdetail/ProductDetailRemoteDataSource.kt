package com.shoppi.app.repository.productdetail

import com.shoppi.app.model.Product
import com.shoppi.app.network.APIClient

class ProductDetailRemoteDataSource(private val apiClient: APIClient):ProductDetailDataSource {
    override suspend fun getProductDetail(productId:String):Product {
        return apiClient.getProductDetail(productId)
    }

}