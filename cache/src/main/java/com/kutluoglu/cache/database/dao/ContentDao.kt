package com.kutluoglu.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.entity.ContentEntity
import io.reactivex.Single

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Dao
interface ContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContents(list: List<ContentEntity>) : List<Long>

    @Query(DbConstants.QUERY_GET_CONTENTS)
    fun getContents() : Single<List<ContentEntity>>
}