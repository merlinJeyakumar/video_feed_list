package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merlin.training_mvvm.domain.entity.*
import com.merlin.training_mvvm.data.database.dao.QuickTextItemDao

@Database(
    entities = arrayOf(
        QuickTextEntity::class
    ),
    version = 7,
    exportSchema = false
)
abstract class RoomDataSource : RoomDatabase() {

    abstract val quickTextItemDao: QuickTextItemDao


}
