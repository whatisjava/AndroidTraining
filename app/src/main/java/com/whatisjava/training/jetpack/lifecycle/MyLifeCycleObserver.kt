package com.whatisjava.training.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

@Deprecated(message = "")
private const val TAG = "MyLifeCycleObserver"
class MyLifeCycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create(){
        Log.d(TAG, "create: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        Log.d(TAG, "start: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume(){
        Log.d(TAG, "resume: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause(){
        Log.d(TAG, "pause: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        Log.d(TAG, "stop: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        Log.d(TAG, "destroy: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun any(){
        Log.d(TAG, "--------any--------")
    }


}