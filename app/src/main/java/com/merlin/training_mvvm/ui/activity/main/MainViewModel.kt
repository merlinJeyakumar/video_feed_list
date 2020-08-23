package com.merlin.training_mvvm.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merlin.training_mvvm.AppController
import com.merlin.training_mvvm.data.repositories.AppSettingsRepository
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseViewModel

class MainViewModel(val appSettingsRepository: AppSettingsRepository) :
    MBaseViewModel(AppController.instance) {
    private val TAG: String = MainViewModel::class.java.simpleName

    private var mutableConnectDisconnectWifi = MutableLiveData<Unit>()
    val connectDisconnectWifi: LiveData<Unit>
        get() = mutableConnectDisconnectWifi

    private var mutableConnectStatusLiveData = MutableLiveData<String>()
    val connectStatusLiveData: LiveData<String>
        get() = mutableConnectStatusLiveData

    private var mutableConnectionStatusLiveData = MutableLiveData<String>()
    val connectionStatusLiveData: LiveData<String>
        get() = mutableConnectionStatusLiveData

    private var mutableConnectionIpAddressLiveData = MutableLiveData<String>()
    val connectionIpAddressLiveData: LiveData<String>
        get() = mutableConnectionIpAddressLiveData

    private var mutableConnectDisconnectButtonText = MutableLiveData<String>()
    val connectDisconnectButtonText: LiveData<String>
        get() = mutableConnectDisconnectButtonText

    override fun subscribe() {
    }
}