package com.molchanov.coreui.router

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Интерфейс для реализации навигации
 */
interface IRouter {

    fun addFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    )

    fun replaceFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    )

    fun replaceFragmentWithMessage(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String,
        message: Bundle
    )

    fun deleteFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    )
}