package com.molchanov.feature_general.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.R
import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.data.dto.categories.MenuCategory
import com.molchanov.feature_general.data.dto.menu.Dish
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
import com.molchanov.feature_general.databinding.FragmentCategoriesBinding
import com.molchanov.feature_general.databinding.FragmentMenuBinding
import com.molchanov.feature_general.di.GeneralComponent
import com.molchanov.feature_general.presentation.categories.CategoryFragment

class MenuFragment :
    BaseVmFragment<FragmentMenuBinding, DefaultAppState, MenuViewModel>() {

    companion object {
        val instance = MenuFragment()

        const val FRAGMENT_TAG = "MenuFragment_fragment_tag"
    }

    //region fragment initialization
    override val viewModel: MenuViewModel by viewModels {
        viewModelFactory
    }

    override fun getViewBinding(): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        GeneralComponent.init(applicationProvider).inject(this)
    }
    //endregion

    private val onRVItemClickListener = object : MenuRvAdapter.OnListItemClickListener {
        override fun onItemClick(data: Dish) {
            onRvItemSelected(data.id)
        }
    }

    private val rvAdapter = MenuRvAdapter(onRVItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAsianMenu()

        initRvAdapter()
        initViewModel()
        initNavigationButton()
    }

    private fun onRvItemSelected(id: Int) {

    }

    private fun initRvAdapter() {
        binding.rvCategories.let {
            it.adapter = rvAdapter
            it.layoutManager = GridLayoutManager(this.context,3)
        }
    }

    private fun initViewModel() {
        viewModel.outLiveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun initNavigationButton(){
        binding.included.appbarMenu.setNavigationOnClickListener {
            router.deleteFragment(
                parentFragmentManager,
                com.google.android.material.R.id.container,
                FRAGMENT_TAG,
                CategoryFragment.FRAGMENT_TAG
            )
        }
    }

    private fun renderData(state: DefaultAppState) {
        when(state){
            is DefaultAppState.Success<*> -> {
                val data = state.data as MenuItemsDto
                rvAdapter.replaceData(data.dishes)
            }
            is DefaultAppState.Error -> {
                //TODO
            }
        }
    }
}