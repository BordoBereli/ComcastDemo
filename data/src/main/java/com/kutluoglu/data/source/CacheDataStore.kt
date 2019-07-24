package com.kutluoglu.data.source

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.DataStore
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Implementation of the [DataStore] interface to provide a means of communicating
 * with the cache data source
 */

open class CacheDataStore @Inject constructor(
    private val cache: Cache
) : DataStore {
    override fun getContents(): Single<List<DataContent>> {
        return cache.getContents()
    }

    override fun getContentDetailByTitle(title: String?): Single<DataContentDetail> {
        return cache.getDataContentDetailByTitle(title)
    }

    override fun saveContents(contents: List<DataContent>): Single<Boolean> {
        return cache.saveDataContents(contents)
    }

    override fun addContentDetail(contentDetail: DataContentDetail): Single<Boolean> {
        return cache.saveDataContentDetail(contentDetail)
    }

    override fun isCached(): Single<Boolean> {
        return cache.isCached()
    }

    override fun isDetailCached(id: String?): Single<Boolean> {
        return cache.isDetailCached(id)
    }
}
