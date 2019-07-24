package com.kutluoglu.data.source

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.repository.DataStore
import com.kutluoglu.data.repository.Remote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Implementation of the [DataStore] interface to provide a means of communicating
 * with the remote data source
 */

open class RemoteDataStore @Inject constructor(
        private val remote: Remote
) : DataStore {
    override fun getContents(): Single<List<DataContent>> {
        return remote.getContents()
    }

    override fun getContentDetailByTitle(title: String?): Single<DataContentDetail> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support getContentDetailByTitle() operation!!!")
    }

    override fun saveContents(contents: List<DataContent>): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support saveContents() operation!!!")
    }

    override fun addContentDetail(contentDetail: DataContentDetail): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support addContentDetail() operation!!!")
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support isCached() operation!!!")
    }

    override fun isDetailCached(id: String?): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support isDetailCached() operation!!!")
    }
}