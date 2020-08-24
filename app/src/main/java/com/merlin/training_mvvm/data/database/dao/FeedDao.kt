package com.merlin.training_mvvm.data.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.support.room.BaseDao

@Dao
abstract class FeedDao : BaseDao<FeedEntity>() {

    @Query("SELECT * FROM tab_feed")
    abstract fun getFeedList(): List<FeedEntity>

    @Query("SELECT * FROM tab_feed")
    abstract fun getFeedListLiveData(): LiveData<List<FeedEntity>>

    @Query("SELECT * FROM tab_feed WHERE id =:feedId")
    abstract fun getFeedItem(feedId: Int): FeedEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFeedEntityList(feedList: List<FeedEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFeedEntity(feedList: FeedEntity)

}
