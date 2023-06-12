package com.molchanov.feature_basket.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_basket.databinding.FragmentBasketRvItemBinding
import com.molchanov.feature_basket.domain.BasketDish

class BasketRvAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<BasketRvAdapter.MenuViewHolder>() {

    companion object {
        const val ACTION_MINUS = false
        const val ACTION_PLUS = true
    }

    private var menuList: MutableList<BasketDish> = mutableListOf()

    interface OnListItemClickListener {
        fun onItemClick(model: BasketRvModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = FragmentBasketRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
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
}

data class BasketRvModel(
    val data: BasketDish,
    val action: Boolean
)