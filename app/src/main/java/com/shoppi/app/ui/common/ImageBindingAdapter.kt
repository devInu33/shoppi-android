package com.shoppi.app.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.shoppi.app.GlideApp

@BindingAdapter("imageUrl") //custom Binding 어댑터 정의
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp
            .with(view)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("circleImageUrl")
fun loadCircleImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp
            .with(view)
            .load(imageUrl)
            .circleCrop()
            .into(view)
    }
}