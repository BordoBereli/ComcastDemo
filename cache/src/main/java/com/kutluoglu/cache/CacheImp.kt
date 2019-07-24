package com.kutluoglu.cache

import com.kutluoglu.cache.database.db.DemoDb
import com.kutluoglu.cache.database.mapper.DbContentDetailMapper
import com.kutluoglu.cache.database.mapper.DbContentMapper
import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.repository.Cache
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 *This class implements the [Cache] repository from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

class CacheImp @Inject constructor(
    private val demoDb: DemoDb,
    private val demoSharedPreference: DemoSharedPreference,
    private val dbContentMapper: DbContentMapper,
    private val dbContentDetailMapper: DbContentDetailMapper
) : Cache {
    override fun saveDataContents(contents: List<DataContent>): Single<Boolean> {
        return if (isExpired()) {
            val idList = demoDb.contentDao().insertAllContents(
                dbContentMapper.mapToCached(contents)
            )
            if (idList.isNotEmpty()) setLastCacheTime()
            Single.just(idList.isNotEmpty())
        } else Single.just(true)
    }

    override fun getContents(): Single<List<DataContent>> {
        return demoDb.contentDao().getContents()
            .map {
                dbContentMapper.mapFromCached(it)
            }
    }

    override fun saveDataContentDetail(dataContentDetail: DataContentDetail): Single<Boolean> {
        val id = demoDb.contentDetailDao().insertContentDetail(
            dbContentDetailMapper.mapToCached(dataContentDetail)
        )
        return Single.just(id > 0)
    }

    override fun getDataContentDetailByTitle(title: String?): Single<DataContentDetail> {
        return demoDb.contentDetailDao().getContentDetailByTitle(title!!)
            .map {
                dbContentDetailMapper.mapFromCached(it)
            }
    }

    override fun isCached(): Single<Boolean> {
        return demoDb.contentDao().getContents().map {
            it.isNotEmpty()
        }
    }

    override fun isDetailCached(title: String?): Single<Boolean> {
        return demoDb.contentDetailDao().getContentDetailByTitle(title!!).map { true }
            .onErrorReturn { false }
    }

    override fun setLastCacheTime() {
        demoSharedPreference.lastCacheTime = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = demoSharedPreference.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME

    }

    companion object {
        private const val EXPIRATION_TIME = (3 * 60 * 1000).toLong() // 3 Minutes for Demo purpose
    }
}