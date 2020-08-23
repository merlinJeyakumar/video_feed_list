package com.merlin.training_mvvm.data.repositories.local

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.data.database.RoomDataSource
import com.merlin.training_mvvm.data.repositories.BaseRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
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

    val quickTextItemDao = roomManager.quickTextItemDao

    override fun getLiveQuickText(): LiveData<List<QuickTextEntity>> {
        return quickTextItemDao.getLiveQuickTextList
    }

}
