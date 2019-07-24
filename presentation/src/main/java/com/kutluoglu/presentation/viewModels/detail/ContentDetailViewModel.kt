package com.kutluoglu.presentation.viewModels.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.GetContentDetailUseCase
import com.kutluoglu.presentation.mapper.DetailViewMapper
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ContentDetailViewModel @Inject constructor(
    private val getContentDetailUseCase: GetContentDetailUseCase,
    private val mapper: DetailViewMapper
) : ViewModel(), DetailContract {
    override fun getContentDetailLiveData(): LiveData<Resource<DetailView>> {
        return detailLiveData
    }

    override fun getContentDetailByTitle(title: String) {
        detailLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getContentDetailUseCase.execute(DetailSubscriber(mapper), title)
    }
}