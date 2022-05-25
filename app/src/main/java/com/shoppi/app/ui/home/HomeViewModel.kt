package com.shoppi.app.ui.home

import com.shoppi.app.ui.common.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppi.app.model.BannerItem
import com.shoppi.app.model.Category
import com.shoppi.app.model.Product
import com.shoppi.app.model.Title
import com.shoppi.app.repository.home.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository):ViewModel() { //상태 홀더 뷰모델로 뷰모델을 상속받는다.
    private val _title = MutableLiveData<Title>()
    val title:LiveData<Title> = _title
    private val _banners= MutableLiveData<List<BannerItem>>()
    val banners:LiveData<List<BannerItem>> = _banners
    private val _openHomeEvent= MutableLiveData<Event<String>>()
    val openHomeEvent:LiveData<Event<String>> = _openHomeEvent

    init{
        loadHomeData()
    }
    fun openBannerDetail(productId:String){
        _openHomeEvent.value = Event(productId)
    }
    private fun loadHomeData(){
        homeRepository.getHomeData()?.let{
            _title.value = it.title
            _banners.value = it.topBanners
        }
    }
}