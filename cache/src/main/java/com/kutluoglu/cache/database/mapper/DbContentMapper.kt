package com.kutluoglu.cache.database.mapper

import com.kutluoglu.cache.database.entity.ContentEntity
import com.kutluoglu.data.model.DataContent
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Map a List of [ContentEntity] instance to and from a List of [DataContent] instance when data is moving between
 * this layer and the Data layer
 */

open class DbContentMapper @Inject constructor() : DbMapper<List<DataContent>, List<ContentEntity>>{
    override fun mapToCached(type: List<DataContent>): List<ContentEntity> {
        val entityList = mutableListOf<ContentEntity>()
        type.map {
            entityList.add(ContentEntity(it.title, it.description, it.iconUrl))
        }
        return entityList
    }

    override fun mapFromCached(type: List<ContentEntity>): List<DataContent> {
        val dataList = mutableListOf<DataContent>()
        type.map {
            dataList.add(DataContent(it.title, it.description, it.iconUrl))
        }
        return dataList
    }
}