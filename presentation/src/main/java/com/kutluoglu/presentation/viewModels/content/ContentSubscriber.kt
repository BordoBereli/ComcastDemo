package com.kutluoglu.presentation.viewModels.content

import androidx.lifecycle.MutableLiveData
import com.kutluoglu.domain.model.Content
import com.kutluoglu.presentation.mapper.ContentViewMapper
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-07-16.
 *
 */

val contentLiveData: MutableLiveData<Resource<List<ContentView>>> = MutableLiveData()
val filteredList = mutableListOf<ContentView>()

class ContentSubscriber (
    private val contentViewMapper: ContentViewMapper
) : DisposableSingleObserver<List<Content>>() {
    override fun onSuccess(domainList: List<Content>) {
        handleSuccesCase(domainList)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error.message)
    }

    private fun handleSuccesCase(domainList: List<Content>) {
        if(domainList.isNotEmpty()) {
            filteredList.clear()
            filteredList.addAll(contentViewMapper.mapToView(domainList))
            contentLiveData.postValue(
                Resource(ResourceState.SUCCESS, contentViewMapper.mapToView(domainList), null))
        } else {
            handleErrorCase("The content list is empty")
        }
    }

    private fun handleErrorCase(error: String?) {
        contentLiveData.postValue(Resource(ResourceState.ERROR, null, error))
    }
}
