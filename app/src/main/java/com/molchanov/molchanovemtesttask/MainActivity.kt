package com.molchanov.molchanovemtesttask

import android.content.Context
import android.os.Bundle
import com.molchanov.core.di.App
import com.molchanov.feature_general.presentation.categories.CategoryFragment
import com.molchanov.molchanovemtesttask.base.BaseActivity
import com.molchanov.molchanovemtesttask.databinding.ActivityMainBinding
import com.molchanov.molchanovemtesttask.di.MainActivityComponent

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        inject(newBase.applicationContext as App)
        super.attachBaseContext(newBase)
    }

    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider())
            .inject(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun addMainFragment() {
        router.addFragment(
            supportFragmentManager,
            R.id.container,
            CategoryFragment.instance,
            CategoryFragment.FRAGMENT_TAG,
        )
    }

    override fun onStart() {
        super.onStart()
        initNavigation()
    }

    private fun initNavigation(){
        with(binding){
            bnvMain.menu.let { menu ->
                bnvMain.setOnItemSelectedListener { item ->
                    when(item){
                        menu.findItem(R.id.bv_item_general) -> {
                            //TODO
                        }
                        menu.findItem(R.id.bv_item_search) -> {
                            //TODO
                        }
                        menu.findItem(R.id.bv_item_basket) -> {
                            //TODO
                        }
                        menu.findItem(R.id.bv_item_account) -> {
                            //TODO
                        }
                    }
                    true
                }
            }
        }
    }
}