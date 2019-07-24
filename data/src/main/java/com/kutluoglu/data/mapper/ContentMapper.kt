package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.domain.model.Content
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class ContentMapper @Inject constructor() : Mapper<List<DataContent>, List<Content>> {
    override fun mapFromEntityToDomainModel(type: List<DataContent>): List<Content> {
        val domainList = mutableListOf<Content>()
        type.map {
            domainList.add(
                Content(it.title, it.description, it.iconUrl)
            )
        }
        return domainList
    }

    override fun mapToEntityFromDomainModel(type: List<Content>): List<DataContent> {
        val dataList = mutableListOf<DataContent>()
        type.map {
            dataList.add(
                DataContent(it.title, it.description, it.iconUrl)
            )
        }
        return dataList
    }
}