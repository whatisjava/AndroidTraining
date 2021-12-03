package com.whatisjava.training

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class TrainingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}