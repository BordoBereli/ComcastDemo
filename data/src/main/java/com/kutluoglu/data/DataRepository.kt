package com.kutluoglu.data

import com.kutluoglu.data.mapper.ContentDetailMapper
import com.kutluoglu.data.mapper.ContentMapper
import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.model.Content
import com.kutluoglu.domain.model.ContentDetail
import com.kutluoglu.domain.repository.ComcastRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Provides an implementation of the [ComcastRepository] interface for communicating to and from
 * data sources
 */

class DataRepository @Inject constructor(
    private val factory: DataStoreFactory,
    private val contentMapper: ContentMapper,
    private val contentDetailMapper: ContentDetailMapper
) : ComcastRepository {
    override fun getContentList(): Single<List<Content>> {
        return factory.retreiveCacheDataStore().isCached()
            .flatMap {
                factory.retreiveDataStore(it).getContents()
            }
            .flatMap {
                saveDataContent(it)
                Single.just(
                    contentMapper.mapFromEntityToDomainModel(it)
                )
            }
    }

    private fun saveDataContent(list: List<DataContent>) {
        factory.retreiveCacheDataStore().saveContents(list)
    }

    override fun getContentDetailBy(title: String?): Single<ContentDetail> {
        return factory.retreiveCacheDataStore().isDetailCached(title)
            .flatMap { cached ->
                if (cached) {
                    factory.retreiveCacheDataStore().getContentDetailByTitle(title)
                } else {
                    factory.retreiveCacheDataStore().getContents()
                        .map {
                            it.first { dataContent -> title.equals(dataContent.title) }
                        }.map {
                            DataContentDetail(it.title, it.description, it.iconUrl)
                        }
                }
            }
            .flatMap {
                saveDetail(it)
                Single.just(
                    contentDetailMapper.mapFromEntityToDomainModel(it)
                )
            }
    }

    private fun saveDetail(detail: DataContentDetail) {
        factory.retreiveCacheDataStore().addContentDetail(detail)
    }
}