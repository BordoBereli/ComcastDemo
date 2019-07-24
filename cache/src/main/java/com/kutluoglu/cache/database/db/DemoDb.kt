package com.kutluoglu.cache.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kutluoglu.cache.database.dao.ContentDao
import com.kutluoglu.cache.database.dao.ContentDetailDao
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.dbUtils.DateConverter
import com.kutluoglu.cache.database.entity.ContentDetailEntity
import com.kutluoglu.cache.database.entity.ContentEntity
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Database(entities = [ContentEntity::class, ContentDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)

abstract class DemoDb @Inject constructor() : RoomDatabase() {
    abstract fun contentDao() : ContentDao
    abstract fun contentDetailDao() : ContentDetailDao

    companion object {
        @Volatile private var INSTANCE: DemoDb? = null
        fun getInstance(context: Context) : DemoDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        DemoDb::class.java, DbConstants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }

}