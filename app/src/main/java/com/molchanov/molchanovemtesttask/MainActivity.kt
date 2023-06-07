package com.molchanov.molchanovemtesttask

import android.os.Bundle
import com.molchanov.molchanovemtesttask.base.BaseActivity
import com.molchanov.molchanovemtesttask.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding(): ActivityMainBinding {
        TODO("Not yet implemented")
    }

    override fun addMainFragment() {
        TODO("Not yet implemented")
    }
}