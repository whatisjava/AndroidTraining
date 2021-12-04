package com.whatisjava.training.http.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun <T : BaseViewModel> getViewModel(clazz: Class<T>) = ViewModelProvider(this).get(clazz)

}