package com.kutluoglu.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Parcelize
data class ContentView (
    val title: String,
    val description: String,
    val iconUrl: String
) : Parcelable {
    fun containsQuery(query: String) : Boolean {
        return title.contains(query, true) && description.contains(query, true)
    }
}