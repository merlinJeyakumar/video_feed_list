package com.merlin.training_mvvm.support.bcRecievers

import android.content.Context
import io.reactivex.Observable

interface NetworkObservingStrategy {

    fun observeNetworkConnectivity(context: Context): Observable<Connectivity>

    fun onError(message: String, exception: Exception)
}