package com.kutluoglu.presentation.viewModels.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.GetContentsUseCase
import com.kutluoglu.presentation.mapper.ContentViewMapper
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ContentViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
    private val mapper: ContentViewMapper
) : ViewModel(), ContentContract {
    override fun getContentLiveData(): LiveData<Resource<List<ContentView>>> {
        return contentLiveData
    }

    override fun loadContents() {
        contentLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getContentsUseCase.execute(ContentSubscriber(mapper))
    }

    override fun search(query: String) {
        val searchList = filteredList.filter {
            it.containsQuery(query)
        }

        handleSearchResult(searchList, query)
    }

    private fun handleSearchResult(searchList: List<ContentView>, query: String) {
        if (searchList.isEmpty()) {
            contentLiveData.postValue(Resource(ResourceState.ERROR, null, "search $query"))
        } else {
            contentLiveData.postValue(Resource(ResourceState.SUCCESS, searchList, null))
        }

    }

    override fun onCleared() {
        super.onCleared()
        getContentsUseCase.dispose()
    }
}