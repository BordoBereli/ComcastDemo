package com.kutluoglu.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.entity.ContentDetailEntity
import io.reactivex.Single

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Dao
interface ContentDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContentDetail(detail: ContentDetailEntity) : Long

    @Query(DbConstants.QUERY_GET_CONTENT_DETAIL_BY_TITLE)
    fun getContentDetailByTitle(title: String) : Single<ContentDetailEntity>
}
