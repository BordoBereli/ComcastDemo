package com.kutluoglu.presentation.viewModels.detail

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-07-16.
 *
 */

interface DetailContract {
    fun getContentDetailLiveData() : LiveData<Resource<DetailView>>
    fun getContentDetailByTitle(title: String)
}