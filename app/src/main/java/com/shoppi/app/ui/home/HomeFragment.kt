package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.model.Product
import com.shoppi.app.ui.common.EventObserver
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels{
        ViewModelFactory(requireContext())
    }
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner //필수적으로 binding의 LifeCycleowner를 Fragment의 LifecycleOwner로 할당해야 함
        setToolbar()
        setTopBanners()
        setNavigation()
    }
    private fun setToolbar() {

        viewModel.title.observe(viewLifecycleOwner) {
            binding.title = it
        }
    }
    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)//dp to pixel 로 getDimension으로 변환
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position -> //SAM
                page.translationX = -position * offset
            }
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.banners.observe(viewLifecycleOwner) {
                    submitList(it)
                }
            }


            TabLayoutMediator(
                binding.viewpagerHomeBannerIndicator,
                this
            ) { _tab, position -> //특정 위치에서 탭의 스타일을 변경하는 람다.

            }.attach()
        }

    }
    private fun setNavigation(){
        viewModel.openHomeEvent.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
                "product_Id" to it
            ))
        })

    }

    //textview 162,72
    // start, end 16, 182

}