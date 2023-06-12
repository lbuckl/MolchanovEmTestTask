package com.molchanov.feature_general.presentation.menu

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.molchanov.core.extensions.customGetParcelable
import com.molchanov.coreui.utils.loadImageFromUrl
import com.molchanov.feature_general.databinding.FragmentMenuDialogBinding
import kotlinx.parcelize.Parcelize

class MenuDialogFragment : DialogFragment() {

    private var _binding: FragmentMenuDialogBinding? = null
    private val binding get() = _binding!!

    private var dishId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuDialogBinding.inflate(inflater, container, false)

        initButtons()

        return binding.root
    }

    private fun initButtons() {
        val uiModelErrorDialog =
            requireArguments().customGetParcelable("ARG_UI_MODEL") as? UiModelMenuDialog
        if (uiModelErrorDialog != null) {
            applyModel(uiModelErrorDialog)
        }
        binding.dialogButton.setOnClickListener {
            dishId = uiModelErrorDialog?.id
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(KEY_ADD_TO_BASKET_RESPONSE to dishId)
            )
            this@MenuDialogFragment.dismiss()
        }
        binding.dialogMenuItemCancel.setOnClickListener { this@MenuDialogFragment.dismiss() }
    }

    private fun applyModel(uiModelMenuDialog: UiModelMenuDialog) {
        with(binding) {
            dialogHeader.text = uiModelMenuDialog.header
            dialogPrice.text = "${uiModelMenuDialog.price}\u20BD"
            dialogWeight.text = "\u00B7 ${uiModelMenuDialog.weight}г"
            dialogDescription.text = uiModelMenuDialog.description
            dialogMenuItemImageView.loadImageFromUrl(uiModelMenuDialog.imageUrl)
        }
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window!!.setLayout(width, height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ErrorDialogFragment"
        private const val ARG_UI_MODEL = "ARG_UI_MODEL"
        private const val KEY_ADD_TO_BASKET_RESPONSE = "KEY_ADD_TO_BASKET_RESPONSE"
        private const val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(
            manager: FragmentManager,
            uiModelDialogFragment: UiModelMenuDialog
        ) {
            val dialogFragment = MenuDialogFragment()
            dialogFragment.arguments = bundleOf(ARG_UI_MODEL to uiModelDialogFragment)
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Int) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner
            ) { _, result ->
                listener.invoke(result.getInt(KEY_ADD_TO_BASKET_RESPONSE))
            }
        }
    }
}

@Parcelize
class UiModelMenuDialog(
    val id: Int,
    val header: String,
    val price: String,
    val weight: String,
    val description: String,
    val imageUrl: String,
) : Parcelable