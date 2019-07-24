package com.kutluoglu.presentation.viewModels.content

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-07-16.
 *
 */

interface ContentContract {
    fun getContentLiveData() : LiveData<Resource<List<ContentView>>>
    fun loadContents()
    fun search(query: String)
}