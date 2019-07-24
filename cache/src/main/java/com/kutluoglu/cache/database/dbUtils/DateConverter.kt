package com.kutluoglu.cache.database.dbUtils

import android.util.Log
import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class DateConverter {
    private var format = SimpleDateFormat("dd/MM/yyyy HH:ss", Locale.getDefault())

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    /**
     * Utility funs
     */
    fun toDateStringFromLong(timestamp: Long?) : String {
        val dateFromDB = toDate(timestamp)
        return fromDateToString(dateFromDB)
    }

    fun fromStringDateToLong(value: String?) : Long {
        val dateFromString = toDateFromString(value)
        return toTimestamp(dateFromString)?.let { it } ?: 0
    }

    fun toDateFromString(value: String?) : Date {
        return when(value) {
            null -> Date()
            else -> try {
                format.parse(value)
            } catch (pe: ParseException) {
                Log.e("Converters", "fromStringToDate: " + pe.message)
                Date()
            }
        }
    }

    fun fromDateToString(date: Date?) : String {
        return when (date) {
            null -> "01/01/1970 00:00"
            else -> format.format(date)
        }
    }

}