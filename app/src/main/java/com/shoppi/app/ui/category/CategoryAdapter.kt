package com.shoppi.app.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemCategoryBinding
import com.shoppi.app.model.Category
import com.shoppi.app.ui.common.CategoryDiffCallback

class CategoryAdapter(private val viewModel: CategoryViewModel):ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCallback()
) {
    private lateinit var binding: ItemCategoryBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) { //oncreate뷰홀더에서 리턴한 뷰홀더가 인자로 들어옴
        holder.bind(getItem(position))
    }
    inner class CategoryViewHolder(private val binding:ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.viewModel = viewModel
            binding.category = category
            binding.executePendingBindings()
        }
    }
}
