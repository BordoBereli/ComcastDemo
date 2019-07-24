package com.kutluoglu.presentation.viewModels.detail

import androidx.lifecycle.MutableLiveData
import com.kutluoglu.domain.model.ContentDetail
import com.kutluoglu.presentation.mapper.DetailViewMapper
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-07-16.
 *
 */

val detailLiveData: MutableLiveData<Resource<DetailView>> = MutableLiveData()

class DetailSubscriber (
    private val detailMapper: DetailViewMapper
) : DisposableSingleObserver<ContentDetail>() {
    override fun onSuccess(domainDetail: ContentDetail) {
        handleSuccessCase(domainDetail)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error.message)
    }

    private fun handleSuccessCase(domainDetail: ContentDetail) {
        detailLiveData.postValue(
            Resource(ResourceState.SUCCESS, detailMapper.mapToView(domainDetail), null)
        )
    }

    private fun handleErrorCase(message: String?) {
        detailLiveData.postValue(Resource(ResourceState.ERROR, null,  message))
    }

}
