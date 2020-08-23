package com.merlin.training_mvvm

import timber.log.Timber

object LogMe {

    @JvmStatic
    fun i(TAG: String, message: String) {
        Timber.i("$TAG: $message")
    }

    @JvmStatic
    fun e(TAG: String, message: String) {
        Timber.e("$TAG: $message")
    }

    @JvmStatic
    fun logException(TAG: String, throwable: Throwable) {
        Timber.e("$TAG: ${throwable.message}")
    }
}