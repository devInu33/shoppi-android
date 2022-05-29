package com.shoppi.app.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.shoppi.app.databinding.ItemProductPromotionBinding
import com.shoppi.app.generated.callback.OnClickListener
import com.shoppi.app.model.Product
import com.shoppi.app.ui.home.HomeViewModel

class PromotionSectionAdapter(private val clickListener: ProductClickListener) :
    ListAdapter<Product, PromotionSectionAdapter.PromotionSectionViewHolder>(ItemPromotionDiff()) {
    inner class PromotionSectionViewHolder(private val binding: ItemProductPromotionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.clickListener = clickListener
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionSectionViewHolder {
        val binding =
            ItemProductPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromotionSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromotionSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemPromotionDiff : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}