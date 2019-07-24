package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import io.reactivex.Single

/**
 * Created by F.K. on 2019-07-16.
 *
 */
interface Cache {
    /**
     * Save list of [DataContent] to the cache
     */
    fun saveDataContents(contents: List<DataContent>) : Single<Boolean>

    /**
     * Retrieve a [List] of [DataContent] from the cache
     */
    fun getContents() : Single<List<DataContent>>

    /**
     * Add a [DataContentDetail] to cache it
     */
    fun saveDataContentDetail(dataContentDetail: DataContentDetail) : Single<Boolean>

    /**
     * Retreive a [DataContentDetail] from cache
     */
    fun getDataContentDetailByTitle(title: String?) : Single<DataContentDetail>

    /**
     * Check whether there is a list of DataContents stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Check whether there is a detail of DataContents stored in the cache.
     *
     * @return true if the detail is cached, otherwise false
     */
    fun isDetailCached(title: String?): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     */
    fun setLastCacheTime()

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    fun isExpired() : Boolean
}