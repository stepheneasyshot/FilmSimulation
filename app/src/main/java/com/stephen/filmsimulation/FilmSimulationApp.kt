package com.stephen.filmsimulation

import android.app.Application
import com.stephen.commonhelper.utils.LogSetting
import com.stephen.commonhelper.utils.infoLog

class FilmSimuApplication : Application() {

    companion object {
        lateinit var instance: FilmSimuApplication
        const val TAG = "FilmSimuApplication"
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        LogSetting.initLogSettings(
            "FilmSimulation[${BuildConfig.VERSION_NAME}]",
            LogSetting.LOG_VERBOSE
        )
        infoLog("=========>onCreate<========", TAG)
    }
}