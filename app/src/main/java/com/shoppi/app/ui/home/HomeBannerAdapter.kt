package com.shoppi.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.model.BannerItem
import com.shoppi.app.GlideApp
import com.shoppi.app.R
import com.shoppi.app.databinding.ItemHomeBannerBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter(private val viewModel:HomeViewModel) :androidx.recyclerview.widget.ListAdapter<BannerItem, HomeBannerAdapter.HomeBannerViewHolder>(
    BannerDiffCallback()
){ //Adapter는 DiffUtil을 인자로 받아 데이터를 뷰홀더에 바인딩함

    private lateinit var binding: ItemHomeBannerBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         //리사이클러뷰의 개별 뷰홀더를 생성하고 뷰를 inflate-> 데이터 바인딩
        return HomeBannerViewHolder(binding) //뷰를 inflate하고 ViewHolder에 전달한다.
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding):RecyclerView.ViewHolder(binding.root){
        //데이터바인딩을 이용하기 위해 바인딩 자체를 넘김(binding.root 를 넘기면 뷰 넘김) 상위 객체엔 뷰를 넘김

        fun bind(banner: BannerItem){//뷰홀더 내부의 뷰참조는 itemView
//            bannerBadgeTextView.background = ColorDrawable(Color.parseColor(banner.badge.backgroundColor)) //colorcode to drawable
            binding.banner=banner
            binding.viewmodel = viewModel
            binding.executePendingBindings() //데이터 즉시 바인딩 메서드. 사용하지 않으면 다음 프레임에 바인딩 됨
        }

    }

}
class BannerDiffCallback:DiffUtil.ItemCallback<BannerItem>(){
    override fun areItemsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
        return oldItem==newItem
    }

}