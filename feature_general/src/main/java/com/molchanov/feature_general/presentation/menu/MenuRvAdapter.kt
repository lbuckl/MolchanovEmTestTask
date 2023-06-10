package com.molchanov.feature_general.presentation.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_general.data.dto.menu.Dish
import com.molchanov.feature_general.databinding.FragmentMenuRvItemBinding

class MenuRvAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<MenuRvAdapter.MenuViewHolder>() {

    private var menuList: MutableList<Dish> = mutableListOf()

    interface OnListItemClickListener {
        fun onItemClick(data: Dish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = FragmentMenuRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(menuList[position])
        }
    }

    fun replaceData(menu: List<Dish>) {
        menuList = menu.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(data: Dish){
            FragmentMenuRvItemBinding.bind(itemView).also {
                it.ivMenuImage.loadImageFromUrl(data.imageUrl)
                it.tvMenuDescription.text = data.name
            }
        }
    }
}