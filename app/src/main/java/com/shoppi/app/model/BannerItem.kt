package com.shoppi.app.model

import com.google.gson.annotations.SerializedName

data class BannerItem(
    @SerializedName("background_img_url")val backgroundImgUrl:String,
    val badge: BannerBadge,
    val label:String,
    @SerializedName("product_detail")val productDetail: Product
)

data class BannerBadge(
    val label:String,
    @SerializedName("background_color")val backgroundColor:String
)
