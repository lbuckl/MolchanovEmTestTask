package com.molchanov.feature_general.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        GeneralComponent.init(applicationProvider).inject(this)
    }
}