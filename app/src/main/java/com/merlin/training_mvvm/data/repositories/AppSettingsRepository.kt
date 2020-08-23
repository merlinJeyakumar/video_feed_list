package com.merlin.training_mvvm.data.repositories

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.data.utils.BaseLiveSharedPreferences
import com.google.gson.Gson
import com.merlin.training_mvvm.domain.datasources.IAppSettingsDataSource
import com.merlin.training_mvvm.support.shared_pref.Prefs


class AppSettingsRepository(
    private val applicationContext: Context,
    private val plainGson: Gson
) : IAppSettingsDataSource {

    private var liveSharedPreferences: BaseLiveSharedPreferences
    private val SP_NAME = "prefs_myapp" //TODO: RENAME

    companion object {

        private var INSTANCE: AppSettingsRepository? = null

        @JvmStatic
        fun getInstance(applicationContext: Context, plainGson: Gson): AppSettingsRepository {
            if (INSTANCE == null) {
                synchronized(AppSettingsRepository::javaClass) {
                    INSTANCE = AppSettingsRepository(applicationContext, plainGson)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }

        private var PREFS_WIFI_CREDENTIALS = "PREFS_WIFI_CREDENTIALS"
        private var PREFS_DAY_CONFIGURATION = "PREFS_DAY_CONFIGURATION"
        private var PREFS_IS_ALL_DAY_ENABLED = "PREFS_IS_ALL_DAY_ENABLED"
        private var PREFS_IS_ALL_STATION_ENABLED = "PREFS_IS_ALL_STATION_ENABLED"
        private var PREFS_IS_ALL_DAY_TIME = "PREFS_IS_ALL_DAY_TIME"
        private var PREFS_IS_ALL_STATION_TIME = "PREFS_IS_ALL_STATION_TIME"
    }

    init {
        Prefs.Builder()
            .setContext(applicationContext)
            .setMode(Context.MODE_PRIVATE)
            .setPrefsName(SP_NAME)
            .build()

        liveSharedPreferences = BaseLiveSharedPreferences(Prefs.getPreferences())
    }

}