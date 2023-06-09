package com.molchanov.feature_general.presentation.categories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.GeneralMenuDto
import com.molchanov.feature_general.data.dto.MenuCategory
import com.molchanov.feature_general.databinding.FragmentCategoriesBinding
import com.molchanov.feature_general.di.GeneralComponent

class CategoryFragment :
    BaseVmFragment<FragmentCategoriesBinding, DefaultAppState, CategoryViewModel>() {

    companion object {
        val instance = CategoryFragment()

        const val FRAGMENT_TAG = "CategoryFragment_fragment_tag"

        const val CATEGORY_BAKERY = 1
        const val CATEGORY_FASTFOOD = 2
        const val CATEGORY_ASIAN = 3
        const val CATEGORY_SOUPS = 4
    }

    //region fragment initialization
    override val viewModel: CategoryViewModel by viewModels {
        viewModelFactory
    }

    override fun getViewBinding(): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(layoutInflater)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        GeneralComponent.init(applicationProvider).inject(this)
    }
    //endregion

    private val onRVItemClickListener = object : CategoryRvAdapter.OnListItemClickListener {
        override fun onItemClick(data: MenuCategory) {
            onRvItemSelected(data.id)
        }
    }

    private val rvAdapter = CategoryRvAdapter(onRVItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()

        initRvAdapter()
        initViewModel()
    }

    private fun onRvItemSelected(id: Int) {
        when (id) {
            CATEGORY_BAKERY -> {
                //TODO
            }
            CATEGORY_FASTFOOD -> {
                //TODO
            }
            CATEGORY_ASIAN -> {
                //TODO
            }
            CATEGORY_SOUPS -> {
                //TODO
            }
        }
    }

    private fun initRvAdapter() {
        binding.rvCategories.let {
            it.adapter = rvAdapter
            it.layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun initViewModel() {
        viewModel.outLiveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: DefaultAppState) {
        when(state){
            is DefaultAppState.Success<*> -> {
                val data = state.data as GeneralMenuDto
                rvAdapter.replaceData(data.categories)
            }
            is DefaultAppState.Error -> {
                //TODO
            }
        }
    }
}