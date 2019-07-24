package com.kutluoglu.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */

@Singleton
class DemoSharedPreference @Inject constructor(context: Context){

    companion object {
        private const val PREF_PACKAGE_NAME = "ccom.kutluoglu.pulselivedemo.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor
    init {
        editor = prefs.edit()
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = prefs.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = editor.putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}