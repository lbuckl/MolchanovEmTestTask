package com.molchanov.feature_basket.presentation

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.red
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_basket.databinding.FragmentBasketRvItemBinding
import com.molchanov.feature_basket.domain.BasketDish

class BasketRvAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<BasketRvAdapter.MenuViewHolder>() {

    private var menuList: MutableList<BasketDish> = mutableListOf()

    interface OnListItemClickListener {
        fun onItemClick(data: BasketDish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = FragmentBasketRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(menuList[position])
        }
    }

    fun replaceData(menu: List<BasketDish>) {
        menuList = menu.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(data: BasketDish){
            FragmentBasketRvItemBinding.bind(itemView).also {
                it.ivDishImage.loadImageFromUrl(data.imageUrl)
                it.actDishNum.setText("${data.quantity}")
                it.tvDishName.text = data.name
            }
        }
    }
}