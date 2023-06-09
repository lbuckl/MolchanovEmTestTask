package com.molchanov.feature_general.presentation.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_general.data.dto.MenuCategory
import com.molchanov.feature_general.databinding.FragmentCategoriesRvItemBinding

class CategoryRvAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<CategoryRvAdapter.CategoryViewHolder>() {

    private var categoryList: MutableList<MenuCategory> = mutableListOf()

    interface OnListItemClickListener {
        fun onItemClick(data: MenuCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = FragmentCategoriesRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(categoryList[position])
        }
    }

    fun replaceData(categories: List<MenuCategory>) {
        categoryList = categories.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(data: MenuCategory){
            FragmentCategoriesRvItemBinding.bind(itemView).also {
                it.ivCategoryImage.loadImageFromUrl(data.imageUrl)
                it.tvCategoryDescription.text = data.name
            }
        }
    }
}