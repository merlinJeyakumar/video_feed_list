package com.merlin.training_mvvm

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.datasources.remote.IRestDataSource
import com.merlin.training_mvvm.ui.activity.main.MainViewModel
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.FeedListFragmentViewModel
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.VideoListFragmentViewModel

class ViewModelFactory private constructor(
    private val appSettingsRepository: AppSettingsRepository,
    private val provideRestDataSource: IRestDataSource,
    val localDataSource: ILocalDataSource
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(
                        appSettingsRepository,
                        provideRestDataSource,
                        localDataSource
                    )
                isAssignableFrom(VideoListFragmentViewModel::class.java) ->
                    VideoListFragmentViewModel(
                        appSettingsRepository,
                        provideRestDataSource,
                        localDataSource
                    )
                isAssignableFrom(FeedListFragmentViewModel::class.java) ->
                    FeedListFragmentViewModel(appSettingsRepository,localDataSource)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideAppDataSource(),
                    Injection.provideRestDataSource(),
                    Injection.provideLocalDataSource()
                )
                    .also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
