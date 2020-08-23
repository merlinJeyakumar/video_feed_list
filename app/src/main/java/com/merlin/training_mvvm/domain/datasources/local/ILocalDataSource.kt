package com.merlin.training_mvvm.domain.datasources.local

import androidx.lifecycle.LiveData
import com.merlin.training_mvvm.domain.entity.QuickTextEntity

interface ILocalDataSource {
    fun getLiveQuickText(): LiveData<List<QuickTextEntity>>
}