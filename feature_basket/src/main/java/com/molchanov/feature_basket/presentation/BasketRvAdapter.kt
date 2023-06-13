package com.molchanov.feature_basket.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_basket.databinding.FragmentBasketRvItemBinding
import com.molchanov.feature_basket.domain.BasketDish

class BasketRvAdapter(
    private val callback: OnListItemClickListener
) : ListAdapter<BasketDish, BasketRvAdapter.MenuViewHolder>(DiffUtilCallBack()) {

    companion object {
        const val ACTION_MINUS = false
        const val ACTION_PLUS = true
    }

    interface OnListItemClickListener {
        fun onItemClick(model: BasketRvModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = FragmentBasketRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(data: BasketDish){
            FragmentBasketRvItemBinding.bind(itemView).also {
                it.ivDishImage.loadImageFromUrl(data.imageUrl)
                it.tvDishPrice.text = "${data.price}₽"
                it.tvDishWeight.text = "· ${data.weight}г"
                it.tvDishName.text = data.name
                it.included.selectNumber.text = "${data.quantity}"

                it.included.selectMinus.setOnClickListener {
                    callback.onItemClick(BasketRvModel(data, ACTION_MINUS))
                }
                it.included.selectPlus.setOnClickListener {
                    callback.onItemClick(BasketRvModel(data, ACTION_PLUS))
                }
            }
        }
    }

    private class DiffUtilCallBack : DiffUtil.ItemCallback<BasketDish>() {
        override fun areItemsTheSame(
            oldItem: BasketDish,
            newItem: BasketDish
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: BasketDish,
            newItem: BasketDish
        ): Boolean {
            return (oldItem == newItem)
        }
    }
}

data class BasketRvModel(
    val data: BasketDish,
    val action: Boolean
)