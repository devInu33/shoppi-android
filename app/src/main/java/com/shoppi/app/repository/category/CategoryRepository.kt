package com.shoppi.app.repository.category

import com.shoppi.app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val  remotesDataSource: CategoryRemotesDataSource){
    suspend fun getCategories():List<Category>{ //suspend는 코루틴 스코프 안에서 실행되도록 강제
//        withContext(Dispatchers.IO) { //네트워크 통신에서는 IO 쓰레드를 사용함
//            remotesDataSource.getCategories() //retrofit라이브러리가 없다면 이렇게 직접 쓰레드를 지정해줘야 UI쓰레드를 블록하지 않음
//        }
        return remotesDataSource.getCategories()
    }
}
