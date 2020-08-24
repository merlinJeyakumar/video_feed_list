package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merlin.training_mvvm.AppController
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseViewModel

class FeedListFragmentViewModel(
    private val appSettingsRepository: AppSettingsRepository,
    private val localDataSource: ILocalDataSource
) : MBaseViewModel(AppController.instance) {
    private val TAG: String? = "FeedListFragmentViewModel"

    var feedEntityLiveData: LiveData<List<FeedEntity>> = localDataSource.getFeedListLiveData()

    override fun subscribe() {
        getFeedList()
    }

    fun addFeed(feedEntity: FeedEntity) {
        localDataSource.insertFeedEntity(feedEntity)
    }

    fun getFeedList(): List<FeedEntity> {
        return localDataSource.getFeedList()
    }
}