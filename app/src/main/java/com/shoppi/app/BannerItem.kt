package com.shoppi.app

import com.google.gson.annotations.SerializedName

data class BannerItem(
    @SerializedName("background_img_url")val backgroundImgUrl:String,
    val badge:BannerBadge,
    val label:String,
    @SerializedName("product_detail")val productDetail: ProductDetail
)
data class HomeData(
    val title:Title,
    @SerializedName("top_banners")val topBanners : List<BannerItem> //Serialized name for Gson
)
data class BannerBadge(
    val label:String,
    @SerializedName("background_color")val backgroundColor:String
)
data class ProductDetail(
    @SerializedName("brand_name")val brandName: String,
    val label:String,
    @SerializedName("discount_rate")val discountRate:Int,
    val price:Int,
    val thumbnail:String,
    @SerializedName("product_id")val productId:String
)