package com.molchanov.coreui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.molchanov.core.extensions.customGetParcelable
import com.molchanov.coreui.databinding.DialogFragmentErrorBinding
import kotlinx.parcelize.Parcelize

class ErrorDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentErrorBinding? = null
    private val binding get() = _binding!!

    private var isTryAgain: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentErrorBinding.inflate(
            inflater,
            container,
            false
        )
        val uiModelErrorDialog =
            requireArguments().customGetParcelable("ARG_UI_MODEL") as? UiModelErrorDialog
        if (uiModelErrorDialog != null) {
            applyModel(uiModelErrorDialog)
        }
        binding.dialogErrorButton.setOnClickListener {
            isTryAgain = true
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(KEY_TRY_AGAIN_RESPONSE to isTryAgain)
            )
            this@ErrorDialogFragment.dismiss()
        }
        binding.dialogErrorCancel.setOnClickListener { this@ErrorDialogFragment.dismiss() }
        return binding.root
    }

    private fun applyModel(uiModelErrorDialog: UiModelErrorDialog) {
        with(binding) {
            dialogErrorTitle.text = getString(uiModelErrorDialog.title)
            dialogErrorDescription.text = uiModelErrorDialog.description?.let { getString(it) }
            dialogErrorImageView.setImageResource(uiModelErrorDialog.imageDrawable)
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
        private const val KEY_TRY_AGAIN_RESPONSE = "KEY_TRY_AGAIN_RESPONSE"
        private const val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(
            manager: FragmentManager,
            uiModelDialogFragment: UiModelErrorDialog
        ) {
            val dialogFragment = ErrorDialogFragment()
            dialogFragment.arguments = bundleOf(ARG_UI_MODEL to uiModelDialogFragment)
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Boolean) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner
            ) { _, result ->
                listener.invoke(result.getBoolean(KEY_TRY_AGAIN_RESPONSE))
            }
        }
    }
}

@Parcelize
class UiModelErrorDialog(
    @StringRes val title: Int,
    @StringRes val description: Int?,
    @DrawableRes val imageDrawable: Int,
) : Parcelable