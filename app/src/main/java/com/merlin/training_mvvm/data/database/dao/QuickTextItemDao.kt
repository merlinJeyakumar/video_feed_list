package com.merlin.training_mvvm.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.merlin.training_mvvm.domain.entity.QuickTextEntity
import com.merlin.training_mvvm.support.room.BaseDao

@Dao
abstract class QuickTextItemDao : BaseDao<QuickTextEntity>() {


    @get:Query("SELECT * FROM " + QuickTextEntity.TABLE_NAME)
    abstract val getLiveQuickTextList: LiveData<List<QuickTextEntity>>

    @get:Query("SELECT COUNT(id) FROM " + QuickTextEntity.TABLE_NAME)
    abstract val quickTextCount: Long
}
