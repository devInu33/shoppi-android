package com.shoppi.app.repository.home

import com.shoppi.app.model.HomeData

class HomeRepository(private val dataSource: HomeDataSource) {
    fun getHomeData(): HomeData?{
        return dataSource.getHomeData()

    }
}