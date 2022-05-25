package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail

class CategoryDetailRepository (private val remoteDataSource: CategoryDetailRemoteDataSource){
    suspend fun getCategoryDetail(cateogryId:String):CategoryDetail{
        return remoteDataSource.getCategoryDetail(cateogryId)
    }
}