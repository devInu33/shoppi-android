package com.shoppi.app.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemCartSectionBinding
import com.shoppi.app.databinding.ItemCartSectionHeaderBinding
import com.shoppi.app.model.CartHeader
import com.shoppi.app.model.CartItem
import com.shoppi.app.model.CartProduct

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM=1

class CartAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() { //2개의 뷰홀더가 필요(헤더, 아이템) .따라서 원형 타입 전달
    //ListAdapter는 SubmitList 메서드를 사용했다
    private val cartProducts= mutableListOf<CartProduct>()
    //ListAdapter와는 달리 데이터의 갯수를 직접 알려줘야 함(관리해야함)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return when(viewType){ //뷰에 타입에 따라 다른 데이터를 맵핑한다.
            VIEW_TYPE_HEADER->HeaderViewHolder(ItemCartSectionHeaderBinding.inflate(inflator, parent, false))
            else->ItemViewHolder(ItemCartSectionBinding.inflate(inflator,parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int { //아이템에 따라 다른 뷰타입을 리턴
        return when(cartProducts[position]){ //ViewType에 따른 정수형 타입을 리턴
            is CartHeader -> VIEW_TYPE_HEADER
            is CartItem -> VIEW_TYPE_ITEM
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder->holder.bind(cartProducts[position] as CartHeader)
            is ItemViewHolder->holder.bind(cartProducts[position] as CartItem)
        }
    }

    override fun getItemCount(): Int {
        return cartProducts.size //직접 갯수를 알려주는 메소드
    }

    class HeaderViewHolder(private val binding:ItemCartSectionHeaderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cartHeader: CartHeader){
            binding.header = cartHeader
            binding.executePendingBindings()
        }
    }
    class ItemViewHolder(private val binding:ItemCartSectionBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem){
            binding.item = cartItem
            binding.executePendingBindings()
        }
    }
    fun submitHeaderAndItemList(items: List<CartItem>){
        val itemGroups = items.groupBy { it.brandName } //groupBy 메서드로 맵을 생성
        val products = mutableListOf<CartProduct>()
        itemGroups.entries.forEach{
            products.add(CartHeader(it.key))
            products.addAll(it.value) //List를 추가
        }
        cartProducts.addAll(products)
        //ListAdapter의 submistList를 직접 해줘야함(어뎁터한테 데이터 변경 통보);
        notifyItemRangeInserted(cartProducts.size, products.size) // 데이터를 한번에 추가
    }
}