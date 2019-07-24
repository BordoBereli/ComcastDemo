package com.kutluoglu.cache.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.kutluoglu.cache.database.db.constant.DbConstants
import kotlinx.android.parcel.Parcelize

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME_CONTENT, primaryKeys = ["title"])

data class ContentEntity (
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "iconUrl") val iconUrl: String
) : Parcelable