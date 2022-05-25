package com.shoppi.app.repository.home

import com.shoppi.app.model.HomeData

interface HomeDataSource { //인터페이스가 되야 하는 이유: 데이터 소스 데이터베이스/파일/네트워크 등 다양한 소스가 있으므로 구현체를 다르게 하기 위해
    fun getHomeData(): HomeData?
}