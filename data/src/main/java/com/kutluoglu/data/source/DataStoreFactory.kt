package com.kutluoglu.data.source

import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.DataStore
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */
open class DataStoreFactory @Inject constructor (
    private val cache: Cache,
    private val cacheDataStore: CacheDataStore,
    private val remoteDataStore: RemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retreiveDataStore(isCached: Boolean) : DataStore {
        return if(isCached && !cache.isExpired()) {
            retreiveCacheDataStore()
        } else {
            retreiveRemoteDataStore()
        }
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retreiveCacheDataStore() : DataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retreiveRemoteDataStore() : DataStore {
        return remoteDataStore
    }
}