package com.shoppi.app.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shoppi.app.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("priceAmount")
fun applyPriceFormat(view:TextView, price:Int){
     //xml의 @string과 동일한 getString() 메서드
     val format =  DecimalFormat("#,###").format(price)
     view.text = view.context.getString(R.string.unit_discount_currency, format) //formatArg를 넣을 수 있음
}
@BindingAdapter("discountRate")
fun applyRateFormat(view:TextView, rate:Int){
     view.text= view.context.getString(R.string.unit_discount_rate,rate)
}
@BindingAdapter("priceAmount", "discountRate")//커스텀 속성을 여러개 정의햇을 때 함수가 실행됨(오버로딩 같이0
fun applyPriceDiscountRate(view:TextView, price:Int, discountRate:Int){
     val discountPrice =  (((100-discountRate)/100.0) *price).roundToInt()
     applyPriceFormat(view,discountPrice)
}
@BindingAdapter("priceAmount", "stirkeThrough")
fun applyPriceAndStirkeStyle(view:TextView, price:Int, strikeThrough:Boolean){
     applyPriceFormat(view,price)
     if(strikeThrough){
          view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
     }
}