package com.merlin.training_mvvm.domain.datasources.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.domain.entity.QuickTextEntity

interface ILocalDataSource {
    fun getLiveQuickText(): LiveData<List<QuickTextEntity>>

    fun putFeedItem(feedEntity: FeedEntity)
    fun getFeedItem(feedId: Int): FeedEntity

    fun getFeedListLiveData(): LiveData<List<FeedEntity>>
    fun getFeedList(): List<FeedEntity>
    fun insertFeedEntity(feedEntity: FeedEntity)
}