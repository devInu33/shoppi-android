package com.shoppi.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class CartProduct  //CartAdapter에서 사용할 수 있도록 2개의 뷰홀더가 사용할 다른 타입을 하나의 sealed class로 묶는다.
data class CartHeader(
    val brandName: String
) : CartProduct()

@Entity(
    tableName = "cart_item"
)
data class CartItem(
    @PrimaryKey @ColumnInfo(name = "product_id") val productId: String,
    val label: String,
    val price: Int,
    @ColumnInfo(name = "brand_name") val brandName: String,
    @ColumnInfo(name = "thumbnail_image_url") val thumbnailImageUrl: String,
    val type: String,
    val amount: Int
) : CartProduct()
