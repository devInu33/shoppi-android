package com.shoppi.app.ui.categorydetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Promotion

import com.shoppi.app.model.TopSelling
import com.shoppi.app.repository.categorydetail.CategoryDetailRepository
import kotlinx.coroutines.launch

class CategoryDetailViewModel(private val repository: CategoryDetailRepository) : ViewModel() {
    private val _topSelling = MutableLiveData<TopSelling>()
    val topSelling: LiveData<TopSelling> = _topSelling
    private val _promotion = MutableLiveData<Promotion>()
    val promotion :LiveData<Promotion> = _promotion


     fun loadCategoryDetail(categoryId: String) {
        viewModelScope.launch {
            val categoryDetail = repository.getCategoryDetail(categoryId)
            Log.d("categorydetail", categoryDetail.toString())//잘나옵니다.
            _topSelling.value = categoryDetail.topSelling
            _promotion.value = categoryDetail.promotion
        }
    }

}