package com.shoppi.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemCategoryTopSellingSectionBinding
import com.shoppi.app.model.TopSelling

class TopSellingSectionAdapter:ListAdapter<TopSelling, TopSellingSectionAdapter.TopSellingSectionViewHolder>(TopSellingDiffCallback()) {
    class TopSellingSectionViewHolder(private val binding:ItemCategoryTopSellingSectionBinding):RecyclerView.ViewHolder(binding.root){
        private val nestedAdapter = TopSellingItemAdapter()
        init{
            binding.rvCategorySection.adapter = nestedAdapter
        }
        fun bind(topSelling: TopSelling){
            binding.title = topSelling.title
            nestedAdapter.submitList(topSelling.categories)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellingSectionViewHolder {
        val binding = ItemCategoryTopSellingSectionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopSellingSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopSellingSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class TopSellingDiffCallback:DiffUtil.ItemCallback<TopSelling>(){
    override fun areItemsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem.title.text == newItem.title.text
    }

    override fun areContentsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem==newItem
    }

}