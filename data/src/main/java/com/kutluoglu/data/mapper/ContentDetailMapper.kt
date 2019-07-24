package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.domain.model.ContentDetail
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class ContentDetailMapper @Inject constructor() : Mapper<DataContentDetail, ContentDetail>{
    override fun mapFromEntityToDomainModel(type: DataContentDetail): ContentDetail {
        return ContentDetail(type.title, type.description, type.iconUrl)
    }

    override fun mapToEntityFromDomainModel(type: ContentDetail): DataContentDetail {
        return DataContentDetail(type.title, type.description, type.iconUrl)
    }
}