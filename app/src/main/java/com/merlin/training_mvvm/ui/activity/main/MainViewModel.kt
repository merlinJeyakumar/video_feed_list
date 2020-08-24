package com.merlin.training_mvvm.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merlin.training_mvvm.AppController
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.datasources.remote.IRestDataSource
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseViewModel

class MainViewModel(
    private val appSettingsRepository: AppSettingsRepository,
    private val restDataSource: IRestDataSource,
    val localDataSource: ILocalDataSource
) :
    MBaseViewModel(AppController.instance) {
    private val TAG: String = MainViewModel::class.java.simpleName
    private val current_page = 1

    private var mutableConnectDisconnectWifi = MutableLiveData<Unit>()
    val connectDisconnectWifi: LiveData<Unit>
        get() = mutableConnectDisconnectWifi

    val liveDataMovieList = MutableLiveData<Pair<Int, String>>()

    override fun subscribe() {
    }

/*
    fun putMovieModel(movieList: MovieModel) {
        val incidentEntityList = mutableListOf<IncidentEntity>()
        movieList.incidentModels.forEachIndexed { index, incident ->
            val incidentEntity = IncidentEntity()
            incidentEntity.item_id = incident.id
            incidentEntity.title = incident.title
            incidentEntity.description = incident.description
            incidentEntity.address = incident.address
            incidentEntity.occured_at = incident.occurred_at
            incidentEntity.updated_at = incident.updated_at
            incidentEntity.url = incident.url
            incidentEntity.media_url = incident.media.image_url
            incidentEntity.media_url_thumb = incident.media.image_url_thumb
            incidentEntity.location_type = incident.location_type
            incidentEntity.location_description = incident.location_description
            incidentEntity.type = incident.type
            incidentEntity.type_properties = incident.type_properties
            incidentEntityList.add(incidentEntity)
        }
        localDataSource.putIncidentList(incidentEntityList)
    }
*/
}