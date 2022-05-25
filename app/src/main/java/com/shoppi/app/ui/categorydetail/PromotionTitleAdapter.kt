package com.shoppi.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemTitleBinding
import com.shoppi.app.model.Title

class PromotionTitleAdapter() :
    ListAdapter<Title, PromotionTitleAdapter.CategoryTitleViewHolder>(TitleDiffCallback()) {

    class CategoryTitleViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(title: Title) {
            binding.title = title
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTitleViewHolder {
        val binding = ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TitleDiffCallback : DiffUtil.ItemCallback<Title>() {
    override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem == newItem
    }
}