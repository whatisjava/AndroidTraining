package com.whatisjava.training.jetpack.lifecycle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityLifeCycleBinding
import com.whatisjava.training.databinding.ActivityMainBinding

private const val TAG = "LifeCycleActivity"

class LifeCycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifeCycleBinding

    private val myLifeCycle by lazy {
        MyLifeCycle(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifeCycleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        lifecycle.addObserver(MyLifeCycleObserver())
        lifecycle.addObserver(MyDefaultLifeCycleObserver())
    }

//    override fun onResume() {
//        super.onResume()
//        myLifeCycle.resume()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        myLifeCycle.destroy()
//    }

}

internal class MyLifeCycle(context: Context){
    fun resume(){
        Log.d(TAG, "resume: ")
    }
    fun destroy(){
        Log.d(TAG, "destroy: ")
    }
}