package com.molchanov.feature_general.presentation.categories

import androidx.fragment.app.viewModels
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.DefaultAppState
import com.molchanov.feature_general.databinding.FragmentCategoriesBinding
import com.molchanov.feature_general.di.GeneralComponent

class CategoryFragment:
    BaseVmFragment<FragmentCategoriesBinding, DefaultAppState, CategoryViewModel>() {

    companion object {
        val instance = CategoryFragment()

        const val FRAGMENT_TAG = "CategoryFragment_fragment_tag"
    }

    override val viewModel: CategoryViewModel by viewModels {
        viewModelFactory
    }

    override fun getViewBinding(): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(layoutInflater)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        GeneralComponent.init(applicationProvider).inject(this)
    }
}