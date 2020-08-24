package com.merlin.training_mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merlin.training_mvvm.data.database.dao.FeedDao
import com.merlin.training_mvvm.domain.entity.*
import com.merlin.training_mvvm.data.database.dao.QuickTextItemDao

@Database(
    entities = arrayOf(
        QuickTextEntity::class,
        FeedEntity::class
    ),
    version = 1,
    exportSchema = false
)
abstract class RoomDataSource : RoomDatabase() {

    abstract val quickTextItemDao: QuickTextItemDao
    abstract val feedDao: FeedDao
}
