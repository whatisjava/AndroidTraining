package com.whatisjava.training

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrainingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}