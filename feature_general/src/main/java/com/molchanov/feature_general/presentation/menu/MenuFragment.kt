package com.molchanov.feature_general.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.menu.Dish
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
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
            onRvItemSelected(
                UiModelMenuDialog(
                    data.name,
                    data.price.toString(),
                    data.weight.toString(),
                    data.description,
                    data.imageUrl
                )
            )
        }
    }

    private val rvAdapter = MenuRvAdapter(onRVItemClickListener)

    private lateinit var chipList: List<Chip>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAsianMenu()

        initToolbarTitle()
        initRvAdapter()
        initViewModel()
        initNavigationButton()
        initChipVariations()
    }

    private fun onRvItemSelected(data: UiModelMenuDialog) {
        MenuDialogFragment.show(
            childFragmentManager,
            data
        )
    }

    private fun initToolbarTitle() {
        arguments?.let { bundle ->
            binding.included.appbarMenu.title = bundle.getString(FRAGMENT_TAG, "")
        }
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

        viewModel.isLoadingEvent.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
    }

    private fun initNavigationButton() {
        binding.included.appbarMenu.setNavigationOnClickListener {
            router.deleteFragment(
                parentFragmentManager,
                com.google.android.material.R.id.container,
                FRAGMENT_TAG,
                CategoryFragment.FRAGMENT_TAG
            )
        }
    }

    private fun initChipVariations() {
        with(binding){
            chipList = listOf(
                chipMenuAll, chipMenuSalad, chipMenuRise, chipMenuFish
            )

            setActiveChipColor(chipList, chipMenuAll)

            binding.chipGroupVariations.setOnCheckedStateChangeListener { group, checkedIds ->
                if (checkedIds.size > 0) {
                    when(group.checkedChipId){
                        chipMenuAll.id -> {
                            viewModel.getVariant(MenuFilters.VARIANT_ALL)
                            setActiveChipColor(chipList, chipMenuAll)
                        }
                        chipMenuSalad.id -> {
                            viewModel.getVariant(MenuFilters.VARIANT_SALAD)
                            setActiveChipColor(chipList, chipMenuSalad)
                        }
                        chipMenuRise.id -> {
                            viewModel.getVariant(MenuFilters.VARIANT_RISE)
                            setActiveChipColor(chipList, chipMenuRise)
                        }
                        chipMenuFish.id -> {
                            viewModel.getVariant(MenuFilters.VARIANT_FISH)
                            setActiveChipColor(chipList, chipMenuFish)
                        }
                    }
                }
            }
        }
    }

    private fun setActiveChipColor(chipList: List<Chip>, active: Chip) {
        chipList.forEach {
            it.setChipBackgroundColorResource(com.molchanov.coreui.R.color.black_alpha_11)
            it.setTextColor(resources.getColor(com.molchanov.coreui.R.color.black))
        }
        active.setChipBackgroundColorResource(com.molchanov.coreui.R.color.main_primary_color)
        active.setTextColor(resources.getColor(com.molchanov.coreui.R.color.white))
    }

    private fun renderData(state: DefaultAppState) {
        when(state){
            is DefaultAppState.Success<*> -> {
                val data = state.data as MenuItemsDto
                rvAdapter.replaceData(data.dishes)
            }
            is DefaultAppState.Error -> {
                showSnackBar("Ошибка полученяи данных")
            }
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun showSnackBar(message: String){
        Snackbar.make(requireContext(), this.requireView(), message, Snackbar.LENGTH_LONG).show()
    }
}