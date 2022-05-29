package com.shoppi.app.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shoppi.app.R
import com.shoppi.app.databinding.FragmentCartBinding
import com.shoppi.app.ui.common.ViewModelFactory

class CartFragment:Fragment() {
    private val viewModel:CartViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var binding:FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner // 현재 액티비티 생명주기를 데이터바인딩 오브젝트에 전달

    }
    fun setListAdapter(){
        val adapter = CartAdapter()
        binding.rvCartItem.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner){
            adapter.submitHeaderAndItemList(it) //ListAdapter의 SubmitList를 직접구현해야 함.
        }
    }
}