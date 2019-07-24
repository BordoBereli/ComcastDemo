package com.kutluoglu.comcastdemo.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kutluoglu.comcastdemo.base.BaseFragment

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Actionbar Utils
 */

fun AppCompatActivity.setupActionbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
}

fun AppCompatActivity.addFragment(fragment: BaseFragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: BaseFragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
