package com.molchanov.molchanovemtesttask.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

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