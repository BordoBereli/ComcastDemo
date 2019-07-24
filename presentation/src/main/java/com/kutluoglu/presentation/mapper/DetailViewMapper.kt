package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.ContentDetail
import com.kutluoglu.presentation.model.DetailView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class DetailViewMapper @Inject constructor() : ViewMapper<ContentDetail, DetailView>{
    override fun mapToView(type: ContentDetail): DetailView {
        return DetailView(type.title, type.description, type.iconUrl)
    }

    override fun mapFromView(type: DetailView): ContentDetail {
        return ContentDetail(type.title, type.description, type.iconUrl)
    }
}