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
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun addMainFragment() {
        //TODO
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