package com.merlin.training_mvvm.support.supportBaseClass


import androidx.multidex.MultiDexApplication

import com.merlin.training_mvvm.support.bcRecievers.ConnectivityReceiver

open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener?) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }


    companion object {

        @get:Synchronized
        var instance: BaseApplication? = null
            private set


    }

    fun clearInstance(){
        instance = null
    }
}
