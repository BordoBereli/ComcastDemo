package com.kutluoglu.comcastdemo.main.features.content

import android.view.View
import com.kutluoglu.comcastdemo.base.ViewContract
import com.kutluoglu.presentation.model.ContentView

/**
 * Created by F.K. on 2019-07-16.
 *
 */

interface ContentContractor : ViewContract {
    fun initializeUI()
    fun contentClicked(content: ContentView, view: View)
    fun setContentRv(adapter: ContentRvAdapter)
    fun setSearchView()
}