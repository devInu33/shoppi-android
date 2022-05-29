package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail

class CategoryDetailRepository (private val remoteDataSource: CategoryDetailRemoteDataSource){
    suspend fun getCategoryDetail(categoryId:String):CategoryDetail{
        return remoteDataSource.getCategoryDetail(categoryId)
    }

}