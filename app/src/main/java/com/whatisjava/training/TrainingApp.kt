package com.whatisjava.training

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.facebook.drawee.backends.pipeline.Fresco
import com.whatisjava.training.jetpack.lifecycle.MyLifeCycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrainingApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

//        ProcessLifecycleOwner.get().lifecycle.addObserver(MyLifeCycleObserver())

        Fresco.initialize(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }

}