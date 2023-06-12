package com.molchanov.feature_general.presentation.categories

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.domain.LocationAndDate
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.data.dto.categories.MenuCategory
import com.molchanov.feature_general.databinding.FragmentCategoriesBinding
import com.molchanov.feature_general.di.GeneralComponent
import com.molchanov.feature_general.presentation.menu.MenuFragment

class CategoryFragment :
    BaseVmFragment<FragmentCategoriesBinding, DefaultAppState, CategoryViewModel>() {

    companion object {
        val instance = CategoryFragment()

        const val FRAGMENT_TAG = "CategoryFragment_fragment_tag"

        const val CATEGORY_BAKERY = 1
        const val CATEGORY_FASTFOOD = 2
        const val CATEGORY_ASIAN = 3
        const val CATEGORY_SOUPS = 4

        const val CATEGORY_NAME_BAKERY = "Пекарни"
        const val CATEGORY_NAME_FASTFOOD = "Фастфуд"
        const val CATEGORY_NAME_ASIAN = "Азиатская кухня"
        const val CATEGORY_NAME_SOUPS = "Супы"

        const val REQUEST_CODE = 1011
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
        observeLocationPermission()
    }

    private fun onRvItemSelected(id: Int) {
        when (id) {
            CATEGORY_BAKERY -> {
                showSnackBar("Функционал временно недоступен")
            }
            CATEGORY_FASTFOOD -> {
                showSnackBar("Функционал временно недоступен")
            }
            CATEGORY_ASIAN -> {
                val bundle = Bundle()

                bundle.putString(MenuFragment.FRAGMENT_TAG, CATEGORY_NAME_ASIAN)

                router.addNewFragmentAndHideCurrent(
                    parentFragmentManager,
                    com.google.android.material.R.id.container,
                    MenuFragment.instance,
                    MenuFragment.FRAGMENT_TAG,
                    FRAGMENT_TAG,
                    bundle
                )
            }
            CATEGORY_SOUPS -> {
                showSnackBar("Функционал временно недоступен")
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

        viewModel.isLoadingEvent.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
    }

    private fun renderData(state: DefaultAppState) {
        when(state){
            is DefaultAppState.Success<*> -> {
                val data = state.data as GeneralMenuDto
                rvAdapter.replaceData(data.categories)
            }
            is DefaultAppState.Error -> {
                showSnackBar("Ошибка полученяи данных")
            }
        }
    }

    private fun renderLoadingState(isLoading: Boolean){
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun observeLocationPermission() {
        viewModel.location.observe(viewLifecycleOwner) {
            if (it){
                viewModel.locationLiveData.observe(viewLifecycleOwner) { data ->
                    renderLocationAndDate(data)
                }
            }
            else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUEST_CODE
                )
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