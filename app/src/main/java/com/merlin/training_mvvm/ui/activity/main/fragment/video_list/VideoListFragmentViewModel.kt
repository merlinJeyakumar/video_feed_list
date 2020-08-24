package com.merlin.training_mvvm.ui.activity.main.fragment.video_list

import android.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.merlin.training_mvvm.AppController
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.datasources.remote.IRestDataSource
import com.merlin.training_mvvm.domain.models.Incident
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseViewModel
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.paging.ItemDataSource
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.paging.ItemDataSourceFactory


class VideoListFragmentViewModel(
    private val appSettingsRepository: AppSettingsRepository,
    private val restDataRepository: IRestDataSource,
    private val localDataSource: ILocalDataSource
) : MBaseViewModel(AppController.instance) {
    private val TAG: String? = "VideoListFragmentViewModel"

    /*private fun putMovieModel(movieList: MovieModel) {
        val incidentEntityList = mutableListOf<IncidentEntity>()
        movieList.Incidents.forEachIndexed { index, incident ->
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
    }*/

    override fun subscribe() {
        initSourceFactory()
    }

    private lateinit var liveDataSource: MutableLiveData<PageKeyedDataSource<Int, Incident>>
    lateinit var networkStateLiveData: MutableLiveData<Pair<Int, String>>
    var itemPagedList: LiveData<PagedList<Incident>>? = null

    private fun initSourceFactory() {
        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.itemLiveDataSource
        networkStateLiveData = itemDataSourceFactory.networkStateLiveData

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(9)
            .setInitialLoadSizeHint(10)
            .setPageSize(ItemDataSource.PAGE_SIZE)
            .build()

        itemPagedList =
            LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
                .build()
    }
}