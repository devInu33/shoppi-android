package com.shoppi.app.repository.category

import com.shoppi.app.model.Category
import com.shoppi.app.network.APIClient

class CategoryRemotesDataSource(private val apiClient: APIClient):CategoryDataSource  {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories() //코루틴 스코프에서 실행되는 함수들은 모두 suspend 키워드를 붙여야함
    }
}