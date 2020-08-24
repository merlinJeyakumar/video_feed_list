package com.merlin.training_mvvm.data.repositories.local

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merlin.training_mvvm.data.database.RoomDataSource
import com.merlin.training_mvvm.data.repositories.BaseRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.domain.entity.QuickTextEntity

class LocalDataRepository(
    private val roomManager: RoomDataSource
) : BaseRepository(), ILocalDataSource {

    companion object {

        private var INSTANCE: LocalDataRepository? = null

        @JvmStatic
        fun getInstance(
            roomManager: RoomDataSource
        ): LocalDataRepository {
            if (INSTANCE == null) {
                synchronized(LocalDataRepository::javaClass) {
                    INSTANCE =
                        LocalDataRepository(roomManager)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    private val quickTextItemDao = roomManager.quickTextItemDao
    private val feedDao = roomManager.feedDao

    override fun getLiveQuickText(): LiveData<List<QuickTextEntity>> {
        return quickTextItemDao.getLiveQuickTextList
    }

    override fun putFeedItem(feedEntity: FeedEntity) {
        return feedDao.insertFeedEntity(feedEntity)
    }

    override fun getFeedItem(feedId: Int): FeedEntity {
        return feedDao.getFeedItem(feedId)
    }

    override fun getFeedListLiveData(): LiveData<List<FeedEntity>> {
        return feedDao.getFeedListLiveData()
    }

    override fun getFeedList(): List<FeedEntity> {
        return feedDao.getFeedList()
    }

    override fun insertFeedEntity(feedEntity: FeedEntity) {
        feedDao.insertFeedEntity(feedEntity)
    }

}
