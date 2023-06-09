package com.molchanov.molchanovemtesttask.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.molchanov.coreui.router.IRouter
import javax.inject.Inject

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    @Inject
    lateinit var router: IRouter

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = getViewBinding()

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setMainFragment(savedInstanceState)
    }

    abstract fun getViewBinding(): T

    private fun setMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            addMainFragment()
    }

    abstract fun addMainFragment()
}