package com.ersinberkealemdaroglu.tripplanapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private var sharedPref: SharedPreferences =
        context.getSharedPreferences("sharedPreferenceUtils", Context.MODE_PRIVATE)

    companion object {
        const val CHECK_BOOKMARK = "com.ersinberkealemdaroglu.BOOKMARK"
    }

    private var isBookmark = false

    fun isBookmark(key: Boolean) {
        isBookmark = key
        sharedPref.edit().putBoolean(CHECK_BOOKMARK, isBookmark).apply()
    }

    fun getIsBookmark(): Boolean {
        return sharedPref.getBoolean(CHECK_BOOKMARK, isBookmark)
    }

}