package com.shoppi.app.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("brand_name")val brandName: String?,
    @SerializedName("represent_image_url") val representImageUrl:String?,
    val label:String,
    @SerializedName("discount_rate")val discountRate:Int,
    val price:Int,
    @SerializedName("thumbnail_image_url")val thumbnail:String?,
    @SerializedName("product_id")val productId:String,
    val descriptions:List<Description>?,
    val type:String?
)
data class Description(
    val id:String,
    @SerializedName("image_url") val imageUrl:String
)