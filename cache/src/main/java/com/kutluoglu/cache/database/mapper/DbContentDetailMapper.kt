package com.kutluoglu.cache.database.mapper

import com.kutluoglu.cache.database.entity.ContentDetailEntity
import com.kutluoglu.data.model.DataContentDetail
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Map a [DataContentDetail] instance to and from a [ContentDetailEntity] instance when data is moving between
 * database and the Cache layer
 */

open class DbContentDetailMapper @Inject constructor() : DbMapper<DataContentDetail, ContentDetailEntity>{
    override fun mapToCached(type: DataContentDetail): ContentDetailEntity {
        return ContentDetailEntity(type.title, type.description, type.iconUrl)
    }

    override fun mapFromCached(type: ContentDetailEntity): DataContentDetail {
        return DataContentDetail(type.title, type.description, type.iconUrl)
    }
}