package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list

import com.merlin.training_mvvm.AppController
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseViewModel

class FeedListFragmentViewModel(
    private val appSettingsRepository: AppSettingsRepository
) : MBaseViewModel(AppController.instance) {
    private val TAG: String? = "DaysFragmentViewModel"

    override fun subscribe() {
    }
}