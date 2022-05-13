package com.whatisjava.training

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrainingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }

//    override fun newImageLoader(): ImageLoader {
//
//    }
}