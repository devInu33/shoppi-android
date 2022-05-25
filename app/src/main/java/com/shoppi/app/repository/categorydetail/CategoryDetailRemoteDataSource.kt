package com.shoppi.app.repository.categorydetail

import android.util.Log
import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.network.APIClient

class CategoryDetailRemoteDataSource(private val apiClient: APIClient):CategoryDetailDataSource {
    override suspend fun getCategoryDetail(cateogoryId:String): CategoryDetail {
        val data =  apiClient.getCategoryDetail(cateogoryId)
        return data
    }

}