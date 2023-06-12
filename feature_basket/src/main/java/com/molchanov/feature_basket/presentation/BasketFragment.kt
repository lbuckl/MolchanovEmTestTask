package com.molchanov.feature_basket.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.domain.LocationAndDate
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_basket.R
import com.molchanov.feature_basket.databinding.FragmentBasketBinding
import com.molchanov.feature_basket.di.BasketComponent
import com.molchanov.feature_basket.domain.BasketDish

class BasketFragment: BaseVmFragment<FragmentBasketBinding, DefaultAppState, BasketViewModel>() {

    companion object {
        val instance = BasketFragment()

        const val FRAGMENT_TAG = "BasketFragment_fragment_tag"
    }

    //region инициализация
    override val viewModel: BasketViewModel by viewModels {
        viewModelFactory
    }

    override fun getViewBinding(): FragmentBasketBinding {
        return FragmentBasketBinding.inflate(layoutInflater)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        BasketComponent.init(applicationProvider).inject(this@BasketFragment)
    }
    //endregion

    private val onRVItemClickListener = object : BasketRvAdapter.OnListItemClickListener {
        override fun onItemClick(model: BasketRvModel) {
            if (model.action == BasketRvAdapter.ACTION_PLUS) viewModel.actionPlus(model.data)
            else viewModel.actionMinus(model.data)
        }
    }

    private val rvAdapter = BasketRvAdapter(onRVItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvAdapter()
        initViewModel()
    }

    private fun initRvAdapter() {
        binding.rvBasket.let {
            it.adapter = rvAdapter
            it.layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun initViewModel() {
        viewModel.outLiveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
        viewModel.getBasket()

        viewModel.locationLiveData.observe(viewLifecycleOwner) { data ->
            renderLocationAndDate(data)
        }

        viewModel.priceLiveData.observe(viewLifecycleOwner) { data->
            binding.btnPay.text = "${resources.getString(R.string.pay)} $data ₽"
        }
    }

    private fun renderData(state: DefaultAppState){
        when(state){
            is DefaultAppState.Success<*> -> {
                rvAdapter.replaceData(state.data as List<BasketDish>)
            }
            is DefaultAppState.Error -> {
                showSnackBar("Ошибка получения данных")
            }
        }
    }

    private fun renderLocationAndDate(data: LocationAndDate) {
        if (data.location.isNotBlank()) binding.included.tvLocationHeader.text = data.location
        else showSnackBar("Ошибка получения геолокации")

        if (data.date.isNotBlank()) binding.included.tvLocationContent.text = data.date
        else showSnackBar("Ошибка получения даты")
    }

    private fun showSnackBar(message: String){
        Snackbar.make(requireContext(), this.requireView(), message, Snackbar.LENGTH_LONG).show()
    }
}