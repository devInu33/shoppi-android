package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val assetLoader = AssetLoader()
//        context?.let{
//            assetLoader.getJsonString(it, "home.json")
//        }
        val toolbarText = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarImage = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homedata = gson.fromJson(homeJsonString, HomeData::class.java)
            toolbarText.text = homedata.title.text
            GlideApp.with(this).load(homedata.title.iconUrl).into(toolbarImage)
            viewPager.adapter = HomeBannerAdapter().apply {
               submitList(homedata.topBanners)
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
    }
    //textview 162,72
    // start, end 16, 182

}