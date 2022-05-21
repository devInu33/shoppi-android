package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private val viewModel: HomeViewModel by viewModels{
            ViewModelFactory(requireContext())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbarText = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarImage = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)


        viewModel.title.observe(viewLifecycleOwner) {
            toolbarText.text = it.text
            GlideApp.with(this).load(it.iconUrl).into(toolbarImage)
        }
        viewPager.adapter = HomeBannerAdapter().apply {
            viewModel.banners.observe(viewLifecycleOwner){
                submitList(it)
            }
        }



        //pageTransformser를 구현하고, setPageTransformer에 전
        val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)//dp to pixel 로 getDimension으로 변환
        val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offset = screenWidth-pageWidth-pageMargin
        viewPager.offscreenPageLimit = 3 //한 페이지에 최대 뷰 갯
        viewPager.setPageTransformer { page, position -> //SAM
            page.translationX = - position* offset
        }
        TabLayoutMediator(viewPagerIndicator, viewPager) { _tab, position -> //특정 위치에서 탭의 스타일을 변경하는 람다.

        }.attach()

    }
    //textview 162,72
    // start, end 16, 182

}