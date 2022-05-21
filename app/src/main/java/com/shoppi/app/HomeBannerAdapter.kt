package com.shoppi.app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :androidx.recyclerview.widget.ListAdapter<BannerItem,HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()){ //Adapter는 DiffUtil을 인자로 받아 데이터를 뷰홀더에 바인딩함


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false) //리사이클러뷰의 개별 뷰홀더를 생성하고 뷰를 inflate-> 데이터 바인딩
        return HomeBannerViewHolder(view) //뷰를 inflate하고 ViewHolder에 전달한다.
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class HomeBannerViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val bannerImageView = view.findViewById<ImageView>(R.id.iv_banner_image)
        private val bannerBadgeTextView = view.findViewById<TextView>(R.id.tv_banner_badge)
        private val bannerTitleText = view.findViewById<TextView>(R.id.tv_banner_title)
        private val detailThumbnail = view.findViewById<ImageView>(R.id.iv_banner_detail_thumbnail)
        private val detailBrandLabel= view.findViewById<TextView>(R.id.tv_banner_detail_brand_label)
        private val detailProductLabel= view.findViewById<TextView>(R.id.tv_banner_detail_product_label)
        private val detailDiscountRate= view.findViewById<TextView>(R.id.tv_banner_detail_discount_rate)
        private val detailDiscountPrice= view.findViewById<TextView>(R.id.tv_banner_detail_discount_price)
        private val detailOriginalPrice= view.findViewById<TextView>(R.id.tv_banner_detail_original_price)

        fun bind(banner:BannerItem){//뷰홀더 내부의 뷰참조는 itemView
            loadImg(banner.backgroundImgUrl, bannerImageView)
            bannerBadgeTextView.text = banner.badge.label
            bannerBadgeTextView.background = ColorDrawable(Color.parseColor(banner.badge.backgroundColor)) //colorcode to drawable
            bannerTitleText.text = banner.label
            loadImg(banner.productDetail.thumbnail, detailThumbnail)
            detailBrandLabel.text = banner.productDetail.brandName
            detailProductLabel.text = banner.productDetail.label
            detailDiscountRate.text = "${banner.productDetail.discountRate}%"
            calculateDiscountRate(detailDiscountPrice,banner.productDetail.discountRate, banner.productDetail.price)
            applyPriceFormat(detailOriginalPrice, banner.productDetail.price)

        }

        private fun calculateDiscountRate(view:TextView,discountRate: Int, price: Int) {
            val discountPrice =  (((100-discountRate)/100.0) *price).roundToInt()
            applyPriceFormat(view,discountPrice)
        }
        private fun applyPriceFormat(textView: TextView,price:Int){
            textView.text =  DecimalFormat("#,###").format(price) + "원"
        }

        private fun loadImg(url:String, imgView:ImageView){
            GlideApp.with(itemView).load(url).into(imgView)
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