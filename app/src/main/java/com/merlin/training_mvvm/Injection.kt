package com.merlin.training_mvvm

import android.content.Context
import com.data.database.RoomDataSource
import com.merlin.training_mvvm.data.database.RoomManager
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.data.repositories.local.LocalDataRepository
import com.merlin.training_mvvm.data.repositories.remote.RestDataRepository
import com.merlin.training_mvvm.data.webservices.IService
import com.merlin.training_mvvm.domain.datasources.local.ILocalDataSource
import com.merlin.training_mvvm.domain.datasources.remote.IRestDataSource
import com.merlin.training_mvvm.data.RestService
import com.google.gson.Gson

object Injection {

    fun provideAppDataSource(): AppSettingsRepository {
        return AppSettingsRepository.getInstance(provideContext(),Gson())
    }

    fun provideRestDataSource(): IRestDataSource {
        return RestDataRepository.getInstance(provideService())
    }

    fun provideLocalDataSource(): ILocalDataSource {
        return LocalDataRepository.getInstance(provideRoomDataSource())
    }

    fun provideService(): IService {
        return RestService.getInstance().getIService()
    }

    fun provideService(url:String): IService {
        return RestService.getInstance(url).getIService()
    }

    fun provideRoomDataSource(): RoomDataSource {
        return RoomManager.getInstance(provideContext())
    }

    fun provideContext(): Context {
        return AppController.instance
    }

}