package com.merlin.training_mvvm

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.facebook.stetho.Stetho
import com.merlin.training_mvvm.ui.activity.CrashActivity
import com.merlin.training_mvvm.support.utills.FileLoggingTree
import org.jetbrains.anko.runOnUiThread
import timber.log.Timber
import java.io.File

class AppController : Application(), LifecycleObserver {

    private val TAG = javaClass.simpleName
    private var mainThread = Looper.getMainLooper().thread

    companion object {
        lateinit var instance: AppController
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun handleUncaughtException(
        thread: Thread,
        e: Throwable
    ) {
        if (thread.id == mainThread.id) {
            runOnUiThread {
                val intent =
                    Intent() //this has to match your intent filter
                intent.setClass(applicationContext, CrashActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(CrashActivity.CRASH_ISSUE, e.message)
                startActivity(intent)
            }
        }
    }

    private fun initialize() {
        instance = this
        /*Thread.setDefaultUncaughtExceptionHandler { thread: Thread, e: Throwable ->
            handleUncaughtException(
                thread,
                e
            )
        }*/
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Timber.plant(Timber.DebugTree())
        getTimberLogFile().delete()
        Timber.plant(FileLoggingTree(getTimberLogFile()))

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    fun getTimberLogFile(): File {
        return File("$filesDir${File.separator}timberLogs", "timberlogfile.txt")
    }
}