package com.shoppi.app.ui.categorydetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.shoppi.app.common.KEY_CATEGORY_ID
import com.shoppi.app.common.KEY_CATEGORY_LABEL
import com.shoppi.app.common.KEY_PRODUCT_ID
import com.shoppi.app.databinding.FragmentCategoryDetailBinding
import com.shoppi.app.ui.common.ViewModelFactory

class CategoryDetailFragment: Fragment() {
    private lateinit var binding:FragmentCategoryDetailBinding
    private val viewModel: CategoryDetailViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //savedInstanceState 이미 destroy 된 이전 Fragment 인스턴스로부터 복구한 것
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        requireArguments().getString(KEY_CATEGORY_ID)?.let{
            setListAdapter(it)
        }


        //concatAdapter

    }
    private fun setToolbar(){
        val categoryLabel =requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title =categoryLabel
    }
    private fun setListAdapter(categoryId:String){
        viewModel.loadCategoryDetail(categoryId)
        val topSellingSectionAdapter = TopSellingSectionAdapter()
        val titleAdapter = PromotionTitleAdapter()
        val promotionAdapter =PromotionSectionAdapter()
        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdapter,titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner){
            topSellingSectionAdapter.submitList(listOf(it))
        }
        viewModel.promotion.observe(viewLifecycleOwner){
            titleAdapter.submitList(listOf(it.title))
            promotionAdapter.submitList(it.items)
        }
    }
}