package com.kutluoglu.comcastdemo.main.features.detail

import com.kutluoglu.comcastdemo.base.ViewContract
import com.kutluoglu.presentation.model.DetailView

/**
 * Created by F.K. on 2019-07-16.
 *
 */

interface DetailContract : ViewContract {
    fun initializeUI(detailView: DetailView)
}